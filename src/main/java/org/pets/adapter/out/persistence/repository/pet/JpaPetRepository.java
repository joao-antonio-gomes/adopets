package org.pets.adapter.out.persistence.repository.pet;

import org.pets.adapter.out.persistence.entity.PetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetRepository extends JpaRepository<PetEntity, Long> {

    Page<PetEntity> findAll(Pageable pageable);
}
