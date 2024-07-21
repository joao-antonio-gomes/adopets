package org.pets.application.petcategory.port;

import org.pets.core.model.PetCategory;

import java.util.List;

public interface PetCategoryUseCase {


    List<PetCategory> findAllPetCategories();

    PetCategory createPetCategory(PetCategory domain);
}
