package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.util.Set;

@RequiredArgsConstructor
@Controller
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory catConverter;
    private final IngredientCommandToIngredient ingConverter;
    private final NoteCommandToNote noteConverter;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        Set<CategoryCommand> categories = source.getCategories();
        Set<IngredientCommand> ingredients = source.getIngredients();

        final Recipe rec = new Recipe();
        rec.setId(source.getId());
        rec.setDescription(source.getDescription());
        rec.setCookTime(source.getCookTime());
        rec.setPrepTime(source.getPrepTime());
        rec.setServings(source.getServings());
        rec.setDifficulty(source.getDifficulty());
        rec.setDirections(source.getDirections());
        rec.setNotes(noteConverter.convert(source.getNotes()));

        for (CategoryCommand c: categories) {
            rec.getCategories().add(catConverter.convert(c));
        }

        for (IngredientCommand i: ingredients) {
            rec.getIngredients().add(ingConverter.convert(i));
        }

        return rec;
    }
}
