package org.pets.adapter.in.pet.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.pets.domain.model.PetStatusAdoption;

import java.util.List;

public record PetsListUpdateStatusRequest(@NotEmpty List<PetUpdateStatusRequest> pets) {

    public record PetUpdateStatusRequest(@NotNull Long id,
                                         @NotNull PetStatusAdoption status) {
    }
}
