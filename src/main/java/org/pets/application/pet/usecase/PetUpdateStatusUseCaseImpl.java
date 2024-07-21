package org.pets.application.pet.usecase;

import org.pets.application.exception.BusinessException;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.application.pet.port.PetUpdateStatusUseCase;
import org.pets.core.model.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetUpdateStatusUseCaseImpl implements PetUpdateStatusUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public PetUpdateStatusUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public void updatePetStatus(Pet pet) {

        final var petToUpdate = petRepositoryPort.findById(pet.getId())
                                                 .orElseThrow(() -> new BusinessException("Pet with id " + pet.getId() + " " + "does not exist"));

        petToUpdate.setStatus(pet.getStatus());

        petRepositoryPort.updatePet(petToUpdate);
    }
}
