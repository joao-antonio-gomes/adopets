package org.pets.adapter.in.pet;

import jakarta.validation.Valid;
import org.pets.adapter.in.pet.request.PetRequest;
import org.pets.adapter.in.pet.request.PetsListUpdateStatusRequest;
import org.pets.adapter.in.pet.response.PetResponse;
import org.pets.application.pet.port.PetCreationUseCase;
import org.pets.application.pet.port.PetQueryUseCase;
import org.pets.application.pet.port.PetUpdateStatusUseCase;
import org.pets.domain.model.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PetResponse> findAllPets() {
        final List<Pet> pets = petQueryUseCase.findAll();

        return pets.stream()
                   .map(petMapper::toResponse)
                   .toList();
    }

    @PostMapping
    public PetResponse createPet(@RequestBody @Valid PetRequest petRequest) {
        final Pet pet = petCreationUseCase.createPet(petMapper.toModel(petRequest));
        return petMapper.toResponse(pet);
    }

    @PatchMapping
    public ResponseEntity<Void> updatePetStatus(@RequestBody @Valid PetsListUpdateStatusRequest petsListUpdateStatusRequest) {
        final List<Pet> pets = petsListUpdateStatusRequest.pets()
                                                          .stream()
                                                          .map(petMapper::toModel)
                                                          .toList();
        petUpdateStatusUseCase.updatePetStatus(pets);
        return ResponseEntity.noContent()
                             .build();
    }
}
