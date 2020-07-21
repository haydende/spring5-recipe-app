package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.HashSet;
import java.util.Set;

public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                      IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Ingredient avocado = new Ingredient("Avocado");
        Ingredient salt = new Ingredient("Salt");
        Ingredient lime = new Ingredient("Lime");

        avocado.setUom(unitOfMeasureRepository.findByDescription("whole").get());
        salt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        lime.setUom(unitOfMeasureRepository.findByDescription("whole").get());

        ingredientRepository.save(avocado);
        ingredientRepository.save(salt);
        ingredientRepository.save(lime);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("guacamole");
        Set<Category> categories = new HashSet<>();
        Set<Ingredient> ingredients = new HashSet<>();

        categories.add(categoryRepository.findByCategoryName("Mexican").get());
        ingredients.add(avocado);
        ingredients.add(salt);
        ingredients.add(lime);

        guacamole.setCategories(categories);
        guacamole.setIngredients(ingredients);

        recipeRepository.save(guacamole);
    }
}
