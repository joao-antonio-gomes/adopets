package org.pets.application.pet.port;

import org.pets.domain.model.Pet;

public interface PetCreationUseCase {
    Pet createPet(Pet model);
}
