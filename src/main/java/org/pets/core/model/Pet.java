package org.pets.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    Long id;
    String name;
    String description;
    String imageUrl;
    PetCategory category;
    LocalDate birthDate;
    PetStatusAdoption status;
}