package org.pets.application.port;

import org.pets.domain.model.PetCategory;

import java.util.List;

public interface PetCategoryUseCasePort {


    List<PetCategory> getAllPetCategories();

    void createPetCategory(PetCategory domain);
}
