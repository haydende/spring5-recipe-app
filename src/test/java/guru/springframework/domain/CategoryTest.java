package guru.springframework.domain;

import junit.framework.TestCase;
import org.junit.Before;

public class CategoryTest extends TestCase {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    public void testGetId() {
        Long idValue = 1L;
        category.setId(1L);

        assertEquals(idValue, category.getId());
    }

    public void testGetCategoryName() {
    }

    public void testGetRecipes() {
    }
}