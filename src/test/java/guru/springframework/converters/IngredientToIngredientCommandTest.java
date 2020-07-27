package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);
    public static final Long UOM1_ID = Long.valueOf(1L);
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(2);

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ing = new Ingredient();
        ing.setId(ID);
        ing.setDescription(DESCRIPTION);
        ing.setAmount(AMOUNT);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM1_ID);

        ing.setUom(uom);

        // when
        IngredientCommand ingC = converter.convert(ing);

        // then
        assertNotNull(ingC);
        assertEquals(ID, ingC.getId());
        assertEquals(DESCRIPTION, ingC.getDescription());
        assertEquals(UOM1_ID, ingC.getUom().getId());
        assertEquals(AMOUNT, ingC.getAmount());
    }

}