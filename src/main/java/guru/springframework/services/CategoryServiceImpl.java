package guru.springframework.services;

import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<String> getCategoryByRecipeDescription(String recipeDescription) {
        return categoryRepository.findCategoryByARecipeDescription("American");
    }
}
