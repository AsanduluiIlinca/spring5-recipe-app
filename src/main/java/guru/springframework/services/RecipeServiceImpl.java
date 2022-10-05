package guru.springframework.services;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CriteriaRecipeRepository;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final CriteriaRecipeRepository criteriaRecipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CriteriaRecipeRepository criteriaRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.criteriaRecipeRepository = criteriaRecipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        Page<Recipe> allRecipe = recipeRepository.findAll(firstPageWithTwoElements);

        List<Recipe> allTenCookTimeRecipe =
                recipeRepository.findAllByCookTime(10, secondPageWithTwoElements);

        return recipes;
    }

    @Override
    public Recipe save(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe newRecipe, Long id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setDescription(newRecipe.getDescription());
                    recipe.setIngredients(newRecipe.getIngredients());
                    recipe.setPrepTime(newRecipe.getPrepTime());
                    recipe.setCookTime(newRecipe.getCookTime());
                    recipe.setServings(newRecipe.getServings());
                    recipe.setDirections(newRecipe.getDirections());
                    recipe.setDifficulty(newRecipe.getDifficulty());
                    return recipeRepository.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setId(id);
                    return recipeRepository.save(newRecipe);
                });
    }

    public List<Recipe> findRecipesByDifficultyAndPrepTime(Difficulty difficulty, Integer prepTime){
        return criteriaRecipeRepository.findRecipesByDifficultyAndPrepTime(difficulty, prepTime);
    }

    public List<Recipe> findRecipesWhichContainsAnIngredient(Ingredient ingredient){
        return criteriaRecipeRepository.findRecipesWhichContainsAnIngredient(ingredient);
    }

    public List<String> findRecipesNameByCategory(String category){
        return criteriaRecipeRepository.findRecipesNameByCategory(category);
    }
}
