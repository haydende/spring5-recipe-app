package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        CategoryCommand cat = new CategoryCommand();
        cat.setId(ID);
        cat.setDescription(DESCRIPTION);

        // when
        Category catC = converter.convert(cat);

        // then
        assertNotNull(catC);
        assertEquals(ID, catC.getId());
        assertEquals(DESCRIPTION, catC.getDescription());
    }

}