package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        Page<Recipe> allRecipe = recipeRepository.findAll(firstPageWithTwoElements);

        List<Recipe> allTenCookTimeRecipe =
                recipeRepository.findAllByCookTime(10, secondPageWithTwoElements);

        System.out.println("findAllRecipesWithDifficultyEasy : " + recipeRepository.findAllRecipesWithDifficulty("EASY"));
        System.out.println("findRecipeNameByCategory " + recipeRepository.findRecipeNameByCategory("American"));
        System.out.println("findRecipeNameByIngredient " + recipeRepository.findRecipeNameByIngredient("steak"));
        System.out.println("findRecipeNameWithNotes " + recipeRepository.findRecipeNameWithNotes());

        return recipes;
    }
}
