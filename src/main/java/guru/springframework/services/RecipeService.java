package guru.springframework.services;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface RecipeService {
    List<Recipe> getAllRecipes();
    Set<Recipe> getRecipes();

    Recipe save(Recipe newRecipe);

    Optional<Recipe> findById(Long id);

    void deleteById(Long id);

    Recipe updateRecipe(Recipe  newRecipe, Long id);

    List<Recipe> findRecipesByDifficultyAndPrepTime(Difficulty difficulty, Integer prepTime);

    List<String> findRecipesNameByCategory(String category);
}
