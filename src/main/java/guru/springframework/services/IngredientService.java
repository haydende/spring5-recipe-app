package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

public interface IngredientService {

    Ingredient findById(Long id);

    IngredientCommand saveCommand(IngredientCommand rec);

    IngredientCommand findCommandById(Long id);

    void deleteById(Long id);
}
