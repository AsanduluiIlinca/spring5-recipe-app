package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;


public class SpecificationUtils {
    public static Specification<Recipe> hasDescription(String description) {
        return (recipe, cq, cb) -> cb.equal(recipe.get("description"), description);
    }

}
