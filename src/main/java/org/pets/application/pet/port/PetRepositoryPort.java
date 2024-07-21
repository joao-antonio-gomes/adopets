package org.pets.application.pet.port;

import org.pets.domain.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface PetRepositoryPort {
    Pet createPet(Pet pet);

    Page<Pet> findAllPaginated(PageRequest pageRequest);

    boolean existsById(Long id);

    Optional<Pet> findById(Long id);

    void updatePets(List<Pet> petsToUpdate);
}
