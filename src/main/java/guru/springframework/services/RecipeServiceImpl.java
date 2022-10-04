package guru.springframework.services;

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
import java.util.Set;



@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final CriteriaRecipeRepository criteriaRecipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CriteriaRecipeRepository criteriaRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.criteriaRecipeRepository = criteriaRecipeRepository;
    }

    public List<Recipe> getAllrecipes(){
        return recipeRepository.findAll();
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

        Ingredient ingredient = allRecipe.stream().findFirst().get().getIngredients().stream().findFirst().get();


        return recipes;
    }
}
