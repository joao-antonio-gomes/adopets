package org.pets.application.petcategory.port;

import org.pets.domain.model.PetCategory;

import java.util.List;
import java.util.Optional;

public interface PetCategoryRepositoryPort {
    List<PetCategory> findAllPetCategories();

    PetCategory createPetCategory(PetCategory petCategory);

    Optional<PetCategory> findById(Long id);

    boolean existsByName(String name);
}
