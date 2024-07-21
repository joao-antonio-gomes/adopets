package org.pets.adapter.in.petcategory;

import jakarta.validation.Valid;
import org.pets.adapter.in.petcategory.request.PetCategoryRequest;
import org.pets.adapter.in.petcategory.response.PetCategoryResponse;
import org.pets.application.petcategory.port.PetCategoryUseCase;
import org.pets.domain.model.PetCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.net.URI.create;

@RestController
@RequestMapping("/pets/categories")
public class PetCategoryRestController {

    private final PetCategoryUseCase petCategoryUseCase;
    private final PetCategoryMapper petCategoryMapper;

    public PetCategoryRestController(PetCategoryUseCase petCategoryUseCase, PetCategoryMapper petCategoryMapper) {
        this.petCategoryUseCase = petCategoryUseCase;
        this.petCategoryMapper = petCategoryMapper;
    }


    @GetMapping
    public ResponseEntity<List<PetCategoryResponse>> findAllPetCategories() {
        final List<PetCategory> allPetCategories = petCategoryUseCase.findAllPetCategories();

        final List<PetCategoryResponse> petCategories = allPetCategories.stream()
                                                                        .map(petCategoryMapper::toResponse)
                                                                        .toList();

        return ResponseEntity.ok(petCategories);
    }

    @PostMapping
    public ResponseEntity<PetCategoryResponse> createPetCategory(@RequestBody @Valid PetCategoryRequest petCategory) {
        final PetCategory petCategoryEntity = petCategoryUseCase.createPetCategory(petCategoryMapper.toDomain(
                petCategory));

        final var uri = "/pets/categories/" + petCategoryEntity.getId();
        return ResponseEntity.created(create(uri))
                             .body(petCategoryMapper.toResponse(petCategoryEntity));
    }
}
