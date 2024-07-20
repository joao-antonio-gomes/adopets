package org.pets.adapter.out.persistence.repository;

import org.pets.adapter.out.persistence.entity.PetCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPetCategoryRepository extends JpaRepository<PetCategoryEntity, Long> {
}
