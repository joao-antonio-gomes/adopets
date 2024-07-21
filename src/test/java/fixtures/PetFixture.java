package fixtures;

import org.pets.domain.model.Pet;
import org.pets.domain.model.PetCategory;
import org.pets.domain.model.PetStatusAdoption;

public interface PetFixture {

    static Pet getDefault(PetCategory petCategory) {
        return new Pet(null, "Cat", null, null, petCategory, null, PetStatusAdoption.AVAILABLE);
    }
}
