package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand catConverter;
    private final IngredientToIngredientCommand ingConverter;
    private final NoteToNoteCommand noteConverter;

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        Set<Category> categories = source.getCategories();
        Set<Ingredient> ingredients = source.getIngredients();

        final RecipeCommand rec = new RecipeCommand();
        rec.setId(source.getId());
        rec.setDescription(source.getDescription());
        rec.setCookTime(source.getCookTime());
        rec.setPrepTime(source.getPrepTime());
        rec.setServings(source.getServings());
        rec.setDifficulty(source.getDifficulty());
        rec.setDirections(source.getDirections());
        rec.setNotes(noteConverter.convert(source.getNotes()));

        for (Category c: categories) {
            rec.getCategories().add(catConverter.convert(c));
        }

        for (Ingredient i: ingredients) {
            rec.getIngredients().add(ingConverter.convert(i));
        }
        return rec;
    }
}
