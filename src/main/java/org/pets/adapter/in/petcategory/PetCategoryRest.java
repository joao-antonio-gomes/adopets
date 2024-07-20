package org.pets.adapter.in.petcategory;

import jakarta.validation.Valid;
import org.pets.adapter.in.petcategory.request.PetCategoryRequest;
import org.pets.adapter.in.petcategory.response.PetCategoryResponse;
import org.pets.application.port.PetCategoryUseCasePort;
import org.pets.domain.model.PetCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets/categories")
public class PetCategoryRest {

    private final PetCategoryUseCasePort petCategoryUseCasePort;
    private final PetCategoryMapper petCategoryMapper;

    public PetCategoryRest(PetCategoryUseCasePort petCategoryUseCasePort,
            PetCategoryMapper petCategoryMapper) {
        this.petCategoryUseCasePort = petCategoryUseCasePort;
        this.petCategoryMapper = petCategoryMapper;
    }


    @GetMapping
    public List<PetCategoryResponse> getAllPetCategories() {
        final List<PetCategory> allPetCategories = petCategoryUseCasePort.getAllPetCategories();

        return allPetCategories.stream()
                               .map(petCategoryMapper::toResponse)
                               .toList();
    }

    @PostMapping
    public void createPetCategory(@RequestBody @Valid PetCategoryRequest petCategory) {
        petCategoryUseCasePort.createPetCategory(petCategoryMapper.toDomain(petCategory));
    }
}
