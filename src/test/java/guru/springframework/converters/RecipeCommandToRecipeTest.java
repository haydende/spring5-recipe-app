package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
            new CategoryCommandToCategory(),
            new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
            new NoteCommandToNote());
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand rec = new RecipeCommand();
        rec.setId(ID);
        rec.setDescription(DESCRIPTION);

        // when
        Recipe recC = converter.convert(rec);

        // then
        assertNotNull(recC);
        assertEquals(ID, recC.getId());
        assertEquals(DESCRIPTION, recC.getDescription());
    }

}