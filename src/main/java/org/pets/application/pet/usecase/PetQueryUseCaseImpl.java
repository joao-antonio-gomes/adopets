package org.pets.application.pet.usecase;

import org.pets.application.pet.port.PetQueryUseCase;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.core.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PetQueryUseCaseImpl implements PetQueryUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public PetQueryUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public Page<Pet> findAllPaginated(PageRequest pageRequest) {
        return petRepositoryPort.findAllPaginated(pageRequest);
    }
}
