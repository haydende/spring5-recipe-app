package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    public static final Long ID = Long.valueOf(1L);
    public static final Long CAT1_ID = Long.valueOf(1L);
    public static final Long CAT2_ID = Long.valueOf(2L);
    public static final Long ING1_ID = Long.valueOf(1L);
    public static final Long ING2_ID = Long.valueOf(2L);
    public static final String DESCRIPTION = "description";
    public static final String DIRECTIONS = "directions";
    public static final String SOURCE = "Source";
    public static final String URL = "Url";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer COOK_TIME = Integer.valueOf(12);
    public static final Integer PREP_TIME = Integer.valueOf(4);
    public static final Integer SERVINGS = Integer.valueOf(2);


    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
            new CategoryToCategoryCommand(),
            new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
            new NoteToNoteCommand()
        );
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe rec = new Recipe();
        rec.setId(ID);
        rec.setDescription(DESCRIPTION);

        // when
        RecipeCommand recC = converter.convert(rec);

        // then
        assertNotNull(recC);
        assertEquals(ID, recC.getId());
        assertEquals(DESCRIPTION, recC.getDescription());
    }
}