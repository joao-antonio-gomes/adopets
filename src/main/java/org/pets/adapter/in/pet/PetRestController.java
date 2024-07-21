package org.pets.adapter.in.pet;

import jakarta.validation.Valid;
import org.pets.adapter.in.pet.request.PetRequest;
import org.pets.adapter.in.pet.request.PetUpdateStatusRequest;
import org.pets.adapter.in.pet.response.PetResponse;
import org.pets.adapter.in.response.ApiResponse;
import org.pets.application.pet.port.PetCreationUseCase;
import org.pets.application.pet.port.PetQueryUseCase;
import org.pets.application.pet.port.PetUpdateStatusUseCase;
import org.pets.domain.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetRestController {

    private final PetCreationUseCase petCreationUseCase;
    private final PetQueryUseCase petQueryUseCase;
    private final PetUpdateStatusUseCase petUpdateStatusUseCase;
    private final PetMapper petMapper;

    public PetRestController(PetCreationUseCase petCreationUseCase,
                             PetQueryUseCase petQueryUseCase,
                             PetUpdateStatusUseCase petUpdateStatusUseCase,
                             PetMapper petMapper) {
        this.petCreationUseCase = petCreationUseCase;
        this.petQueryUseCase = petQueryUseCase;
        this.petUpdateStatusUseCase = petUpdateStatusUseCase;
        this.petMapper = petMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PetResponse>> findAllPaginated(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int pageSize) {
        final Page<Pet> pets = petQueryUseCase.findAllPaginated(PageRequest.of(page, pageSize));
        final Page<PetResponse> petResponses = pets.map(petMapper::toResponse);

        final var apiResponse = new ApiResponse<>(petResponses.getContent(),
                                                  petResponses.getTotalElements(),
                                                  petResponses.getTotalPages(),
                                                  petResponses.getNumber(),
                                                  pageSize);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@RequestBody @Valid PetRequest petRequest) {
        final Pet pet = petCreationUseCase.createPet(petMapper.toModel(petRequest));

        final var uri = URI.create("/pets/" + pet.getId());
        return ResponseEntity.created(uri)
                             .body(petMapper.toResponse(pet));
    }

    @PatchMapping
    public ResponseEntity<Void> updatePetStatus(@RequestBody @Valid List<PetUpdateStatusRequest> petsToUpdate) {
        final List<Pet> pets = petsToUpdate.stream()
                                           .map(petMapper::toModel)
                                           .toList();
        petUpdateStatusUseCase.updatePetStatus(pets);
        return ResponseEntity.noContent()
                             .build();
    }
}
