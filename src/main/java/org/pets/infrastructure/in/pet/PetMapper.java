package org.pets.infrastructure.in.pet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.pets.core.model.Pet;
import org.pets.core.model.PetStatusAdoption;
import org.pets.infrastructure.in.pet.request.PetRequest;
import org.pets.infrastructure.in.pet.request.PetUpdateStatusRequest;
import org.pets.infrastructure.in.pet.response.PetResponse;
import org.pets.infrastructure.out.persistence.entity.PetEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PetMapper {

    PetResponse toResponse(Pet pet);

    @Mapping(target = "category.id", source = "categoryId")
    Pet toModel(PetRequest petRequest);

    Pet toModel(PetEntity petEntity);

    PetEntity toEntity(Pet pet);

    @Mapping(target = "status", source = "adopted", qualifiedByName = "booleanToStatus")
    Pet toModel(PetUpdateStatusRequest petUpdateStatusRequest);

    @Named("booleanToStatus")
    default PetStatusAdoption booleanToStatus(Boolean adopted) {
        return adopted != null && adopted ? PetStatusAdoption.ADOPTED : PetStatusAdoption.AVAILABLE;
    }
}
