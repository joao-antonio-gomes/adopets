package org.pets.adapter.in.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import fixtures.PetCategoryFixture;
import fixtures.PetFixture;
import fixtures.PetRequestFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.pets.adapter.in.BaseRestControllerTest;
import org.pets.adapter.in.pet.request.PetRequest;
import org.pets.adapter.in.pet.request.PetUpdateStatusRequest;
import org.pets.application.pet.port.PetCreationUseCase;
import org.pets.application.petcategory.port.PetCategoryUseCase;
import org.pets.domain.model.PetCategory;
import org.pets.domain.model.PetStatusAdoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PetRestControllerTest extends BaseRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PetCreationUseCase petCreationUseCase;

    @Autowired
    private PetCategoryUseCase petCategoryUseCase;

    @Test
    @DirtiesContext
    void whenHaveOnePet_thenFindOnePet() throws Exception {
        final var petCategory = createPetCategory();
        petCreationUseCase.createPet(PetFixture.getDefault(petCategory));

        mvc.perform(get("/pets"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.data", hasSize(1)))
           .andExpect(jsonPath("$.data[0].name", is("Cat")))
           .andExpect(jsonPath("$.data[0].category.name", is("Cats")))
           .andExpect(jsonPath("$.data[0].status", is("AVAILABLE")))
           .andExpect(jsonPath("$.pagination.totalElements", is(1)))
           .andExpect(jsonPath("$.pagination.totalPages", is(1)))
           .andExpect(jsonPath("$.pagination.page", is(0)))
           .andExpect(jsonPath("$.pagination.pageSize", is(10)));
    }

    @Test
    @DirtiesContext
    void givenCustomPagination_whenHaveOnePet_thenFindOnePet() throws Exception {
        final var petCategory = createPetCategory();
        petCreationUseCase.createPet(PetFixture.getDefault(petCategory));

        mvc.perform(get("/pets?page=0&pageSize=20"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.data", hasSize(1)))
           .andExpect(jsonPath("$.data[0].name", is("Cat")))
           .andExpect(jsonPath("$.data[0].category.name", is("Cats")))
           .andExpect(jsonPath("$.data[0].status", is("AVAILABLE")))
           .andExpect(jsonPath("$.pagination.totalElements", is(1)))
           .andExpect(jsonPath("$.pagination.totalPages", is(1)))
           .andExpect(jsonPath("$.pagination.page", is(0)))
           .andExpect(jsonPath("$.pagination.pageSize", is(20)));
    }

    @Test
    @DirtiesContext
    void whenDontHavePet_thenReturnEmptyList() throws Exception {
        mvc.perform(get("/pets"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    @DirtiesContext
    void whenHaveValidPet_thenCreatePet() throws Exception {
        final var petCategory = createPetCategory();
        final String requestJson = objectMapper.writeValueAsString(PetRequestFixture.getValid(petCategory.getId()));

        mvc.perform(post("/pets").contentType(MediaType.APPLICATION_JSON)
                                 .content(requestJson))
           .andDo(print())
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id", is(1)))
           .andExpect(jsonPath("$.name", is("Cat")))
           .andExpect(jsonPath("$.category.name", is("Cats")))
           .andExpect(jsonPath("$.status", is("AVAILABLE")));
    }

    @Test
    @DirtiesContext
    void whenHaveValidPetButDidntHavePetCategory_thenThrowError() throws Exception {
        final String requestJson = objectMapper.writeValueAsString(PetRequestFixture.getValid(33L));

        mvc.perform(post("/pets").contentType(MediaType.APPLICATION_JSON)
                                 .content(requestJson))
           .andDo(print())
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.status", is(400)))
           .andExpect(jsonPath("$.error", is("Pet category does not exist")));
    }

    @ParameterizedTest
    @MethodSource("fixtures.PetRequestFixture#provideInvalidPet")
    void givenInvalidPet_whenCreating_thenThrowError(PetRequest petRequest) throws Exception {
        final String requestJson = objectMapper.writeValueAsString(petRequest);
        mvc.perform(post("/pets").contentType(MediaType.APPLICATION_JSON)
                                 .content(requestJson))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.status", is(400)));
    }

    private PetCategory createPetCategory() {
        return petCategoryUseCase.createPetCategory(PetCategoryFixture.DEFAULT);
    }

    @Test
    @DirtiesContext
    void givenInexistentPet_whenUpdatePetStatus_thenThrowError() throws Exception {
        final var petsToUpdate = Stream.of(new PetUpdateStatusRequest(1L, PetStatusAdoption.AVAILABLE),
                                           new PetUpdateStatusRequest(2L, PetStatusAdoption.ADOPTED));
        final String requestJson = objectMapper.writeValueAsString(petsToUpdate);

        mvc.perform(patch("/pets").contentType(MediaType.APPLICATION_JSON)
                                  .content(requestJson))
           .andDo(print())
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.status", is(400)))
           .andExpect(jsonPath("$.error", is("Pets with id [1, 2] does not exist")));
    }

    @Test
    @DirtiesContext
    void whenUpdateValidPetStatus_thenUpdate() throws Exception {
        final var petCategory = createPetCategory();
        final var petDefault = PetFixture.getDefault(petCategory);
        final var petOne = petCreationUseCase.createPet(petDefault);
        petDefault.setStatus(PetStatusAdoption.ADOPTED);
        final var petTwo = petCreationUseCase.createPet(petDefault);
        final var petsToUpdate = Stream.of(new PetUpdateStatusRequest(petOne.getId(), PetStatusAdoption.ADOPTED),
                                           new PetUpdateStatusRequest(petTwo.getId(), PetStatusAdoption.AVAILABLE));
        final String requestJson = objectMapper.writeValueAsString(petsToUpdate);

        mvc.perform(patch("/pets").contentType(MediaType.APPLICATION_JSON)
                                  .content(requestJson))
           .andDo(print())
           .andExpect(status().isNoContent());

        mvc.perform(get("/pets"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.data", hasSize(2)))
           .andExpect(jsonPath("$.data[0].status", is("ADOPTED")))
           .andExpect(jsonPath("$.data[1].status", is("AVAILABLE")));
    }
}