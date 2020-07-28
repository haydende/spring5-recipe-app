package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recConvert;
    private final RecipeToRecipeCommand recC_Convert;

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Starting getRecipes method...");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            return recipeOptional.get();
        }
        throw new RuntimeException("Recipe has not been found!");
    }

    @Override
    public RecipeCommand saveCommand(RecipeCommand rec) {
        Recipe recC = recConvert.convert(rec);
        return recC_Convert.convert(recipeRepository.save(recC));
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        Recipe rec = recipeRepository.findById(id).orElse(null);
        if (rec == null) {
            throw new RuntimeException("Recipe not found");
        }
        return recC_Convert.convert(rec);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
