package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        Category cat = new Category();
        cat.setId(ID);
        cat.setDescription(DESCRIPTION);

        // when
        CategoryCommand catC = converter.convert(cat);

        // then
        assertNotNull(catC);
        assertEquals(ID, catC.getId());
        assertEquals(DESCRIPTION, catC.getDescription());
    }

}