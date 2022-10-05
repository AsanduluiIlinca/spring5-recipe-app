package guru.springframework.repositories;

import guru.springframework.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaRecipeRepositoryImpl implements CriteriaRecipeRepository {

    EntityManager em;

    public CriteriaRecipeRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Recipe> findRecipesByDescription(String description) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);

        Root<Recipe> recipe = cq.from(Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        if (description != null) {
            predicates.add(cb.equal(recipe.get("description"), description));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Recipe> findRecipesByDifficultyAndPrepTime(Difficulty difficulty, Integer prepTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);

        Root<Recipe> recipe = cq.from(Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        if (difficulty != null) {
            predicates.add(cb.equal(recipe.get("difficulty"), difficulty));
        }
        if (prepTime != null) {
            predicates.add(cb.equal(recipe.get("prepTime"), prepTime));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Recipe> findRecipesWhichContainsAnIngredient(Ingredient ingredient) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);

        Root<Ingredient> ingredientRoot = cq.from(Ingredient.class);
        Path<Recipe> recipePath = ingredientRoot.get("recipe");
        cq.select(recipePath);
        cq.where(cb.equal(ingredientRoot, ingredient));
        return em.createQuery(cq).getResultList();

    }

    @Override
    public List<String> findRecipesNameByCategory(String category) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = cb.createQuery(Recipe.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);
        SetJoin<Category, Recipe> recipes = categoryRoot.join(Category_.recipes);
        criteriaQuery.where(cb.equal(categoryRoot.get(Category_.description), category));
        List<Recipe> r = em.createQuery(criteriaQuery.select(recipes)).getResultList();
        List<String> result = new ArrayList<>();
        r.forEach(recipe -> result.add(recipe.getDescription()));
        return result;

    }

}
