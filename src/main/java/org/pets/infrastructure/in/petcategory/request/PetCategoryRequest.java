package org.pets.infrastructure.in.petcategory.request;

import jakarta.validation.constraints.NotBlank;
import org.pets.infrastructure.out.persistence.entity.PetCategoryEntity;

import java.io.Serializable;

/**
 * DTO for {@link PetCategoryEntity}
 */
public record PetCategoryRequest(@NotBlank String name) implements Serializable {
}