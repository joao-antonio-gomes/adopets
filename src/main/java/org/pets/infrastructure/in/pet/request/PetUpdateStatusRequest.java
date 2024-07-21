package org.pets.infrastructure.in.pet.request;

import jakarta.validation.constraints.NotNull;

public record PetUpdateStatusRequest(@NotNull Boolean adopted) {
}
