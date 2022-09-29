package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

    public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByDescription(String description);

    @Query(value = "SELECT category.description FROM category WHERE category.id IN (SELECT category_id from recipe_category where recipe_id IN (select recipe.id from recipe where recipe.description=:recipeDescription))", nativeQuery = true)
    List<String> findCategoryByARecipeDescription(String recipeDescription);
}
