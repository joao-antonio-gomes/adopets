package org.pets.adapter.in.petcategory.request;

import jakarta.validation.constraints.NotBlank;
import org.pets.adapter.out.persistence.entity.PetCategoryEntity;

import java.io.Serializable;

/**
 * DTO for {@link PetCategoryEntity}
 */
public record PetCategoryRequest(@NotBlank String name) implements Serializable {
}