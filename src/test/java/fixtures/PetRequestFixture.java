package fixtures;

import org.junit.jupiter.params.provider.Arguments;
import org.pets.infrastructure.in.pet.request.PetRequest;
import org.pets.core.model.PetStatusAdoption;

import java.util.stream.Stream;

public interface PetRequestFixture {
    static PetRequest getValid(Long categoryId) {
        return new PetRequest("Cat", null, null, null, categoryId, PetStatusAdoption.AVAILABLE);
    }

    static Stream<Arguments> provideInvalidPet() {
        return Stream.of(Arguments.of(new PetRequest(null, null, null, null, null, null)),
                         Arguments.of(new PetRequest("", null, null, null, null, null)),
                         Arguments.of(new PetRequest("C", null, null, null, null, null)),
                         Arguments.of(new PetRequest("Cat", null, null, null, null, null)),
                         Arguments.of(new PetRequest("Cat", null, null, null, 1L, null)));
    }
}
