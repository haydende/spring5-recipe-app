package guru.springframework.controllers;

import exceptions.NotFoundException;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping({"/recipe"})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/ingredients";
    }

    @RequestMapping("{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @RequestMapping("{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        log.debug("Deleting recipe " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    // @RequestMapping(name = "recipe/new", method = RequestMethod.POST) // older way of setting up POST command
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return "recipe/recipeform";
        }

        RecipeCommand savedCommand = recipeService.saveCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show/";
    }

}
