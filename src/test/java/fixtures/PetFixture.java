package fixtures;

import org.pets.core.model.Pet;
import org.pets.core.model.PetCategory;
import org.pets.core.model.PetStatusAdoption;

public interface PetFixture {

    static Pet getDefault(PetCategory petCategory) {
        return new Pet(null, "Cat", null, null, petCategory, null, PetStatusAdoption.AVAILABLE);
    }
}
