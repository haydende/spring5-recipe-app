package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class IngredientControllerTest {

    IngredientController controller;

    @Mock
    IngredientRepository repository;

    @Mock
    IngredientService service;

    MockMvc mockMvc;

    public static final Long ID = Long.valueOf(1L);
    public static final Long UOM_ID = Long.valueOf(1L);
    public static final String DESCRIPTION = "Description";

    @BeforeEach
    void setUp() {
        log.debug("Conducting setup...");
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIngredientById() throws Exception {
        Ingredient ing = new Ingredient();
        ing.setId(ID);

        when(service.findById(anyLong())).thenReturn(ing);

        mockMvc.perform(get("/ingredient/1/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("ingredient/show"));
    }

    @Test
    void getNewForm() throws Exception {
        IngredientCommand command = new IngredientCommand();

        mockMvc.perform(get("/ingredient/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("ingredient/ingredientform"))
            .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void postNewRecipeForm() throws Exception {
        IngredientCommand command  = new IngredientCommand();
        command.setId(ID);

        when(service.saveCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", ""))

            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/1/show/"));
    }

    @Test
    void getUpdateForm() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);

        when(service.findCommandById(anyLong())).thenReturn(command);
        mockMvc.perform(get("/ingredient/1/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("ingredient/ingredientform"));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/ingredient/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

        verify(service, times(1)).deleteById(anyLong());
    }
}