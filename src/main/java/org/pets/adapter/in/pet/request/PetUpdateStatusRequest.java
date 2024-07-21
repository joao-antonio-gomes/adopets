package org.pets.adapter.in.pet.request;

import jakarta.validation.constraints.NotNull;
import org.pets.domain.model.PetStatusAdoption;

public record PetUpdateStatusRequest(@NotNull Long id,
                                     @NotNull PetStatusAdoption status) {
}
