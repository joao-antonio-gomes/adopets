package org.pets.application.pet.port;

import org.pets.domain.model.Pet;

import java.util.List;

public interface PetQueryUseCase {
    List<Pet> findAll();
}
