package org.pets.infrastructure.out.persistence.repository.pet;

import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.core.model.Pet;
import org.pets.infrastructure.in.pet.PetMapper;
import org.pets.infrastructure.out.persistence.entity.PetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaPetRepositoryAdapter implements PetRepositoryPort {

    private final JpaPetRepository jpaPetRepository;
    private final PetMapper petMapper;

    public JpaPetRepositoryAdapter(JpaPetRepository jpaPetRepository, PetMapper petMapper) {
        this.jpaPetRepository = jpaPetRepository;
        this.petMapper = petMapper;
    }

    @Override
    public Pet createPet(Pet pet) {
        final PetEntity petEntity = petMapper.toEntity(pet);
        final PetEntity petEntitySaved = jpaPetRepository.save(petEntity);

        return petMapper.toModel(petEntitySaved);
    }

    @Override
    public Page<Pet> findAllPaginated(Long id, PageRequest pageRequest) {
        Page<PetEntity> petPaged;
        if (id == null) {
            petPaged = jpaPetRepository.findAll(pageRequest);
        } else {
            petPaged = jpaPetRepository.findAllPaginatedById(id, pageRequest);
        }

        return petPaged.map(petMapper::toModel);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaPetRepository.existsById(id);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        final Optional<PetEntity> petEntityOptional = jpaPetRepository.findById(id);

        return petEntityOptional.map(petMapper::toModel);

    }

    @Override
    public void updatePet(Pet petToUpdate) {
        final PetEntity pet = petMapper.toEntity(petToUpdate);
        jpaPetRepository.save(pet);
    }
}
