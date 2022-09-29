package guru.springframework.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<String> getCategoryByRecipeDescription(String recipeDescription);
}
