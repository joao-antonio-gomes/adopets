package org.pets.application.petcategory.usecase;

import org.pets.application.exception.BusinessException;
import org.pets.application.petcategory.port.PetCategoryRepositoryPort;
import org.pets.application.petcategory.port.PetCategoryUseCase;
import org.pets.core.model.PetCategory;
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
        if (petCategoryRepositoryPort.existsByName(petCategory.getName())) {
            throw new BusinessException("Pet category already exists");
        }

        return petCategoryRepositoryPort.createPetCategory(petCategory);
    }
}
