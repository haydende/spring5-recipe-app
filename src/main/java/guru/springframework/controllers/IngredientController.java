package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @RequestMapping("/{id}/show")
    public String getIngredientById(@PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(Long.valueOf(id)));
        return "ingredient/show";
    }

    @RequestMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute("ingredient", new IngredientCommand());
        return "ingredient/ingredientform";
    }

    @RequestMapping("{id}/delete")
    public String deleteById(@PathVariable String id, Model model) {
        log.debug("Deleting ingredient with id: " + id);
        ingredientService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
