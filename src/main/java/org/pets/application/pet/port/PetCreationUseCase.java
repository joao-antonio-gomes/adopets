package org.pets.application.pet.port;

import org.pets.core.model.Pet;

public interface PetCreationUseCase {
    Pet createPet(Pet model);
}
