package guru.springframework.repositories;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

import java.util.List;

public interface CriteriaRecipeRepository {
    List<Recipe> findRecipesByDescription(String description);
    List<Recipe> findRecipesByDifficultyAndPrepTime(Difficulty difficulty, Integer prepTime);

    List<Recipe> findRecipesWhichContainsAnIngredient(Ingredient ingredient);
}
