package org.pets.infrastructure.in.petcategory;

import org.mapstruct.Mapper;
import org.pets.core.model.PetCategory;
import org.pets.infrastructure.in.petcategory.request.PetCategoryRequest;
import org.pets.infrastructure.in.petcategory.response.PetCategoryResponse;
import org.pets.infrastructure.out.persistence.entity.PetCategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PetCategoryMapper {

    PetCategory toDomain(PetCategoryRequest petCategoryRequest);

    PetCategory toDomain(PetCategoryEntity petCategoryEntity);

    PetCategoryResponse toResponse(PetCategory petCategory);

    PetCategoryEntity toEntity(PetCategory petCategory);
}
