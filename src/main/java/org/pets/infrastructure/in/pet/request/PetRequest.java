package org.pets.infrastructure.in.pet.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.pets.core.model.PetStatusAdoption;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.pets.infrastructure.out.persistence.entity.PetEntity}
 */
public record PetRequest(@Size(min = 3) @NotBlank String name,
                         String description,
                         String imageUrl,
                         LocalDate birthDate,
                         @NotNull Long categoryId,
                         @NotNull PetStatusAdoption status) implements Serializable {
}