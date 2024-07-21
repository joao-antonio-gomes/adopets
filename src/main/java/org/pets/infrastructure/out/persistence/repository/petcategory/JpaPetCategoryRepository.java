package org.pets.infrastructure.out.persistence.repository.petcategory;

import org.pets.infrastructure.out.persistence.entity.PetCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPetCategoryRepository extends JpaRepository<PetCategoryEntity, Long> {
    boolean existsByName(String name);
}
