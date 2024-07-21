package org.pets.application.pet.port;

import org.pets.core.model.Pet;

public interface PetUpdateStatusUseCase {
    void updatePetStatus(Pet pet);
}
