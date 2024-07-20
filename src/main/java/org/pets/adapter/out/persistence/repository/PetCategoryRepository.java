package org.pets.adapter.out.persistence.repository;

import org.pets.adapter.in.petcategory.PetCategoryMapper;
import org.pets.adapter.out.persistence.entity.PetCategoryEntity;
import org.pets.application.port.PetCategoryRepositoryAdapter;
import org.pets.domain.model.PetCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCategoryRepository implements PetCategoryRepositoryAdapter {

    private final JpaPetCategoryRepository jpaPetCategoryRepository;
    private final PetCategoryMapper petCategoryMapper;

    public PetCategoryRepository(JpaPetCategoryRepository jpaPetCategoryRepository,
            PetCategoryMapper petCategoryMapper) {
        this.jpaPetCategoryRepository = jpaPetCategoryRepository;
        this.petCategoryMapper = petCategoryMapper;
    }

    @Override
    public List<PetCategory> getAllPetCategories() {
        final List<PetCategoryEntity> categoryRepositoryAll = jpaPetCategoryRepository.findAll();
        return categoryRepositoryAll.stream()
                                    .map(petCategoryMapper::toDomain)
                                    .toList();
    }

    @Override
    public void createPetCategory(PetCategory petCategory) {
        final PetCategoryEntity entity = petCategoryMapper.toEntity(petCategory);
        jpaPetCategoryRepository.save(entity);
    }
}
