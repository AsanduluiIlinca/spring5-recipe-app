package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    @Query(value = "SELECT recipe.description FROM recipe WHERE LOWER(difficulty)=LOWER(:#{#difficulty})", nativeQuery = true)
    List<String> findAllRecipesWithDifficulty(String difficulty);

    @Query(value = "SELECT recipe.description FROM recipe WHERE recipe.id IN (SELECT recipe_id from recipe_category where category_id=(select id from category where category.description=:categoryName))", nativeQuery = true)
    List<String> findRecipeNameByCategory(String categoryName);

    @Query(value = "SELECT recipe.description FROM recipe JOIN ingredient ON recipe.id= ingredient.recipe_id WHERE LOWER(ingredient.description) = LOWER(:ingredient)", nativeQuery = true)
    List<String> findRecipeNameByIngredient(String ingredient);

    @Query(value = "SELECT recipe.description FROM recipe JOIN notes ON recipe.id=notes.recipe_id", nativeQuery = true)
    List<String> findRecipeNameWithNotes();

    List<Recipe> findAllByCookTime(Integer cookTime, Pageable pageable);

}
