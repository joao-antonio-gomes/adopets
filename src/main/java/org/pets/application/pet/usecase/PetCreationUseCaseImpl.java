package org.pets.application.pet.usecase;

import org.pets.application.exception.BusinessException;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.application.petcategory.port.PetCategoryRepositoryPort;
import org.pets.core.model.Pet;
import org.pets.core.model.PetCategory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetCreationUseCaseImpl implements org.pets.application.pet.port.PetCreationUseCase {

    private final PetRepositoryPort petRepositoryPort;
    private final PetCategoryRepositoryPort petCategoryRepositoryPort;

    public PetCreationUseCaseImpl(PetRepositoryPort petRepositoryPort,
                                  PetCategoryRepositoryPort petCategoryRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
        this.petCategoryRepositoryPort = petCategoryRepositoryPort;
    }

    @Override
    public Pet createPet(Pet pet) {
        final Optional<PetCategory> petCategoryOptional = petCategoryRepositoryPort.findById(pet.getCategory()
                                                                                                .getId());

        if (petCategoryOptional.isEmpty()) {
            throw new BusinessException("Pet category does not exist");
        }

        pet.setCategory(petCategoryOptional.get());

        return petRepositoryPort.createPet(pet);
    }
}
