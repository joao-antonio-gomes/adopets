package org.pets.adapter.in.petcategory.response;

import org.pets.adapter.out.persistence.entity.PetCategoryEntity;

import java.io.Serializable;

/**
 * DTO for {@link PetCategoryEntity}
 */
public record PetCategoryResponse(Long id, String name) implements Serializable {
}