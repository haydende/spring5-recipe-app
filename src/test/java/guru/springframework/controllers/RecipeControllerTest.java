package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class RecipeControllerTest {

    public static final Long ID = Long.valueOf(1L);
    public static final String DESCRIPTION = "Description";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        log.debug("Conducting setup...");
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    }

    @Test
    void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        log.debug("recipeController is null? " + (recipeController == null));

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show/"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/show"));
    }

    @Test
    void getNewForm() throws Exception {
        RecipeCommand command = new RecipeCommand();

        mockMvc.perform(get("/recipe/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/recipeform"))
            .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postNewRecipeForm() throws Exception {
        RecipeCommand command  = new RecipeCommand();
        command.setId(ID);

        when(recipeService.saveCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", ""))

            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/1/show/"));
    }

    @Test
    void getUpdateForm() throws Exception {
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(ID);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/recipeform"));
    }
}