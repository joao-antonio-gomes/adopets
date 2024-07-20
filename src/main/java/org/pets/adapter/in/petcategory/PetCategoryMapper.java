package org.pets.adapter.in.petcategory;

import org.mapstruct.Mapper;
import org.pets.adapter.in.petcategory.request.PetCategoryRequest;
import org.pets.adapter.in.petcategory.response.PetCategoryResponse;
import org.pets.adapter.out.persistence.entity.PetCategoryEntity;
import org.pets.domain.model.PetCategory;

@Mapper(componentModel = "spring")
public interface PetCategoryMapper {

    PetCategory toDomain(PetCategoryRequest petCategoryRequest);

    PetCategory toDomain(PetCategoryEntity petCategoryEntity);

    PetCategoryResponse toResponse(PetCategory petCategory);

    PetCategoryEntity toEntity(PetCategory petCategory);
}
