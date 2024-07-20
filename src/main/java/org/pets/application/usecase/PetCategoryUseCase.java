package org.pets.application.usecase;

import org.pets.application.port.PetCategoryRepositoryAdapter;
import org.pets.application.port.PetCategoryUseCasePort;
import org.pets.domain.model.PetCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCategoryUseCase implements PetCategoryUseCasePort {

    private final PetCategoryRepositoryAdapter petCategoryRepositoryAdapter;

    public PetCategoryUseCase(PetCategoryRepositoryAdapter petCategoryRepositoryAdapter) {
        this.petCategoryRepositoryAdapter = petCategoryRepositoryAdapter;
    }

    @Override
    public List<PetCategory> getAllPetCategories() {
        return petCategoryRepositoryAdapter.getAllPetCategories();
    }

    @Override
    public void createPetCategory(PetCategory petCategory) {
        petCategoryRepositoryAdapter.createPetCategory(petCategory);
    }
}
