package org.pets.adapter.out.persistence.repository.petcategory;

import org.pets.adapter.in.petcategory.PetCategoryMapper;
import org.pets.adapter.out.persistence.entity.PetCategoryEntity;
import org.pets.application.petcategory.port.PetCategoryRepositoryPort;
import org.pets.domain.model.PetCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaPetCategoryRepositoryAdapter implements PetCategoryRepositoryPort {

    private final JpaPetCategoryRepository jpaPetCategoryRepository;
    private final PetCategoryMapper petCategoryMapper;

    public JpaPetCategoryRepositoryAdapter(JpaPetCategoryRepository jpaPetCategoryRepository,
                                           PetCategoryMapper petCategoryMapper) {
        this.jpaPetCategoryRepository = jpaPetCategoryRepository;
        this.petCategoryMapper = petCategoryMapper;
    }

    @Override
    public List<PetCategory> findAllPetCategories() {
        final List<PetCategoryEntity> categoryRepositoryAll = jpaPetCategoryRepository.findAll();
        return categoryRepositoryAll.stream()
                                    .map(petCategoryMapper::toDomain)
                                    .toList();
    }

    @Override
    public PetCategory createPetCategory(PetCategory petCategory) {
        final PetCategoryEntity entity = petCategoryMapper.toEntity(petCategory);
        final PetCategoryEntity entitySaved = jpaPetCategoryRepository.save(entity);
        return petCategoryMapper.toDomain(entitySaved);
    }

    @Override
    public Optional<PetCategory> findById(Long id) {
        return jpaPetCategoryRepository.findById(id)
                                       .map(petCategoryMapper::toDomain);
    }
}
