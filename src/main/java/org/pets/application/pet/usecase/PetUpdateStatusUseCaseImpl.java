package org.pets.application.pet.usecase;

import org.pets.application.exception.BusinessException;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.application.pet.port.PetUpdateStatusUseCase;
import org.pets.domain.model.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetUpdateStatusUseCaseImpl implements PetUpdateStatusUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public PetUpdateStatusUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public void updatePetStatus(List<Pet> pets) {
        List<Long> nonExistingPetIds = new ArrayList<>();

        pets.forEach(pet -> {
            if (!petRepositoryPort.existsById(pet.getId())) {
                nonExistingPetIds.add(pet.getId());
            }
        });

        if (!nonExistingPetIds.isEmpty()) {
            throw new BusinessException("Pets with id " + nonExistingPetIds + " does not exist");
        }

        List<Pet> petsToUpdate = new ArrayList<>();

        pets.forEach(pet -> {
            Optional<Pet> existingPet = petRepositoryPort.findById(pet.getId());

            Pet petToUpdate = existingPet.get();
            petToUpdate.setStatus(pet.getStatus());
            petsToUpdate.add(petToUpdate);
        });

        petRepositoryPort.updatePets(petsToUpdate);
    }
}
