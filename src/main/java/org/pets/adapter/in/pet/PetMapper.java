package org.pets.adapter.in.pet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pets.adapter.in.pet.request.PetRequest;
import org.pets.adapter.in.pet.request.PetsListUpdateStatusRequest;
import org.pets.adapter.in.pet.response.PetResponse;
import org.pets.adapter.out.persistence.entity.PetEntity;
import org.pets.domain.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetResponse toResponse(Pet pet);

    @Mapping(target = "category.id", source = "categoryId")
    Pet toModel(PetRequest petRequest);

    Pet toModel(PetEntity petEntity);

    PetEntity toEntity(Pet pet);

    Pet toModel(PetsListUpdateStatusRequest.PetUpdateStatusRequest petUpdateStatusRequest);
}
