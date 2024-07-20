package org.pets.application.petcategory.usecase;

import org.pets.application.petcategory.port.PetCategoryRepositoryPort;
import org.pets.application.petcategory.port.PetCategoryUseCase;
import org.pets.domain.model.PetCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCategoryUseCaseImpl implements PetCategoryUseCase {

    private final PetCategoryRepositoryPort petCategoryRepositoryPort;

    public PetCategoryUseCaseImpl(PetCategoryRepositoryPort petCategoryRepositoryPort) {
        this.petCategoryRepositoryPort = petCategoryRepositoryPort;
    }

    @Override
    public List<PetCategory> findAllPetCategories() {
        return petCategoryRepositoryPort.findAllPetCategories();
    }

    @Override
    public PetCategory createPetCategory(PetCategory petCategory) {
        return petCategoryRepositoryPort.createPetCategory(petCategory);
    }
}
