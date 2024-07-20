package org.pets.adapter.out.persistence.repository.pet;

import org.pets.adapter.in.pet.PetMapper;
import org.pets.adapter.out.persistence.entity.PetEntity;
import org.pets.application.pet.port.PetRepositoryPort;
import org.pets.domain.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaPetAdapter implements PetRepositoryPort {

    private final JpaPetRepository jpaPetRepository;
    private final PetMapper petMapper;

    public JpaPetAdapter(JpaPetRepository jpaPetRepository,
                         PetMapper petMapper) {
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
    public List<Pet> findAll() {
        final List<PetEntity> petEntities = jpaPetRepository.findAll();
        return petEntities.stream()
                          .map(petMapper::toModel)
                          .toList();
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
    public void updatePets(List<Pet> petsToUpdate) {
        final List<PetEntity> petEntities = petsToUpdate.stream()
                                                        .map(petMapper::toEntity)
                                                        .toList();
        jpaPetRepository.saveAll(petEntities);
    }
}
