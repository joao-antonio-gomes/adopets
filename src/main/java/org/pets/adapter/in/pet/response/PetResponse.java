package org.pets.adapter.in.pet.response;

import org.pets.adapter.in.petcategory.response.PetCategoryResponse;
import org.pets.domain.model.PetStatusAdoption;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.pets.adapter.out.persistence.entity.PetEntity}
 */
public record PetResponse(Long id,
                          String name,
                          String description,
                          String imageUrl,
                          PetCategoryResponse category,
                          LocalDate birthDate,
                          PetStatusAdoption status) implements Serializable {
}