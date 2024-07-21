package org.pets.infrastructure.in.pet.response;

import org.pets.infrastructure.in.petcategory.response.PetCategoryResponse;
import org.pets.core.model.PetStatusAdoption;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.pets.infrastructure.out.persistence.entity.PetEntity}
 */
public record PetResponse(Long id,
                          String name,
                          String description,
                          String imageUrl,
                          PetCategoryResponse category,
                          LocalDate birthDate,
                          PetStatusAdoption status) implements Serializable {
}