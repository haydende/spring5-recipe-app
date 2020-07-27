package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);
    public static final Long UOM1_ID = Long.valueOf(1L);
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(2);

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand ing = new IngredientCommand();
        ing.setId(ID);
        ing.setDescription(DESCRIPTION);
        ing.setAmount(AMOUNT);

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM1_ID);
        ing.setUom(uom);

        // when
        Ingredient ingC = converter.convert(ing);

        // then
        assertNotNull(ingC);
        assertEquals(ID, ingC.getId());
        assertEquals(DESCRIPTION, ingC.getDescription());
        assertEquals(AMOUNT, ingC.getAmount());
        assertEquals(UOM1_ID, ingC.getUom().getId());
    }

}