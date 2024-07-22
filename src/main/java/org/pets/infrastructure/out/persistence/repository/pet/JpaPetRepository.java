package org.pets.infrastructure.out.persistence.repository.pet;

import org.pets.infrastructure.out.persistence.entity.PetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetRepository extends JpaRepository<PetEntity, Long> {

    Page<PetEntity> findAll(Pageable pageable);

    Page<PetEntity> findAllPaginatedById(Long id, PageRequest pageRequest);
}
