package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingConvert;
    private final IngredientCommandToIngredient ingC_Convert;

    @Override
    public Ingredient findById(Long id) {
        Optional<Ingredient> ing = ingredientRepository.findById(id);
        if (ing.isPresent()) {
            return ing.get();
        }
        throw new RuntimeException("No ingredient found!");
    }

    @Override
    public IngredientCommand saveCommand(IngredientCommand ingC) {
        Ingredient ing = ingC_Convert.convert(ingC);
        return ingConvert.convert(ingredientRepository.save(ing));
    }

    @Override
    public IngredientCommand findCommandById(Long id) {
        Optional<Ingredient> ing = ingredientRepository.findById(id);
        if (ing.isPresent()) {
            return ingConvert.convert(ing.get());
        }
        throw new RuntimeException("No ingredient found!");
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
