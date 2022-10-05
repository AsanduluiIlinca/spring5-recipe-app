package guru.springframework.controllers;


import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/recipe")
    Recipe addRecipe(@RequestBody Recipe newRecipe) {
        return recipeService.save(newRecipe);
    }

    @GetMapping("/recipe/{id}")
    Optional<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeService.findById(id);
    }

    @PutMapping("/recipe/{id}")
    Recipe updateRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id) {

        return recipeService.updateRecipe(newRecipe, id);
    }

    @DeleteMapping("/recipe/{id}")
    void deleteEmployee(@PathVariable Long id) {
        recipeService.deleteById(id);
    }

    @GetMapping("/search/{difficulty}")
    List<Recipe> getRecipeByDifficultyAndPrepTime(@PathVariable Difficulty difficulty, @RequestBody Integer prepTime)  {
        return recipeService.findRecipesByDifficultyAndPrepTime(difficulty, prepTime);
    }

    @GetMapping("/search/recipe/")
    List<String> getRecipesNameByCategory(@RequestBody String category)  {
        return recipeService.findRecipesNameByCategory(category);
    }
}
