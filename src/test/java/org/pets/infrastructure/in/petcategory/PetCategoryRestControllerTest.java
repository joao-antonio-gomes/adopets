package org.pets.infrastructure.in.petcategory;

import com.fasterxml.jackson.databind.ObjectMapper;
import fixtures.PetCategoryFixture;
import fixtures.PetCategoryRequestFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.pets.infrastructure.in.BaseRestControllerTest;
import org.pets.infrastructure.in.petcategory.request.PetCategoryRequest;
import org.pets.application.petcategory.port.PetCategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PetCategoryRestControllerTest extends BaseRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PetCategoryUseCase petCategoryUseCase;

    @Test
    @DirtiesContext
    void whenHaveOnePetCategory_thenFindOnePetCategory() throws Exception {
        petCategoryUseCase.createPetCategory(PetCategoryFixture.DEFAULT);

        mvc.perform(get("/pets/categories"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].name", is("Cats")));

    }

    @Test
    @DirtiesContext
    void whenDontHavePetCategory_thenReturnEmptyList() throws Exception {
        mvc.perform(get("/pets/categories"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(0)));

    }

    @ParameterizedTest
    @MethodSource("fixtures.PetCategoryRequestFixture#provideIncorrectPetCategories")
    void whenHaveIncorrectPetCategory_thenThrowError(PetCategoryRequest petCategoryRequest) throws Exception {
        final String requestJson = objectMapper.writeValueAsString(petCategoryRequest);
        mvc.perform(post("/pets/categories").contentType(MediaType.APPLICATION_JSON)
                                            .content(requestJson))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.status", is(400)))
           .andExpect(jsonPath("$.error", is("Invalid values")))
           .andExpect(content().string(containsString("\"name\":[\"must not be blank\"]")));
    }

    @Test
    @DirtiesContext
    void whenHaveCorrectPetCategory_thenCreatePetCategory() throws Exception {
        final String requestJson = objectMapper.writeValueAsString(PetCategoryRequestFixture.VALID);
        mvc.perform(post("/pets/categories").contentType(MediaType.APPLICATION_JSON)
                                            .content(requestJson))
           .andDo(print())
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id", is(1)))
           .andExpect(jsonPath("$.name", is("Cats")));
    }

    @Test
    @DirtiesContext
    void givenExistingPetCategory_whenCreate_thenThrowError() throws Exception {
        petCategoryUseCase.createPetCategory(PetCategoryFixture.DEFAULT);
        final String requestJson = objectMapper.writeValueAsString(PetCategoryRequestFixture.VALID);
        mvc.perform(post("/pets/categories").contentType(MediaType.APPLICATION_JSON)
                                            .content(requestJson))
           .andDo(print())
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.status", is(400)))
           .andExpect(jsonPath("$.error", is("Pet category already exists")));
    }
}