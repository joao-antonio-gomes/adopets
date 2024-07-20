package org.pets.application.pet.usecase;

import org.pets.application.pet.port.PetQueryUseCase;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.domain.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetQueryUseCaseImpl implements PetQueryUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public PetQueryUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public List<Pet> findAll() {
        return petRepositoryPort.findAll();
    }
}
