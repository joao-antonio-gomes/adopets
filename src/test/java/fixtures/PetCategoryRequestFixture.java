package fixtures;

import org.junit.jupiter.params.provider.Arguments;
import org.pets.adapter.in.petcategory.request.PetCategoryRequest;

import java.util.stream.Stream;

public interface PetCategoryRequestFixture {

    PetCategoryRequest VALID = new PetCategoryRequest("Cats");

    PetCategoryRequest WITH_NAME_NULL = new PetCategoryRequest(null);

    PetCategoryRequest WITH_NAME_BLANK = new PetCategoryRequest("");

    static Stream<Arguments> provideIncorrectPetCategories() {
        return Stream.of(Arguments.of(WITH_NAME_NULL), Arguments.of(WITH_NAME_BLANK));
    }

}
