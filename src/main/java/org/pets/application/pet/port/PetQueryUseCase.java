package org.pets.application.pet.port;

import org.pets.core.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PetQueryUseCase {
    Page<Pet> findAllPaginated(PageRequest pageRequest);
}
