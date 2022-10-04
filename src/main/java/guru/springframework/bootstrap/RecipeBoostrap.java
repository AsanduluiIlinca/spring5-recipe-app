package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBoostrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBoostrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(3);

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("expected UOM Not Found");
        }

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("expected UOM Not Found");
        }

        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setCookTime(10);
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamoleNotes.setRecipe(guacamoleRecipe);
        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("Kosher salt ", new BigDecimal(5), teaspoonUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("Kosher salt ", new BigDecimal(5), teaspoonUom, guacamoleRecipe));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacamoleRecipe);

        Recipe spicyChickenRecipe = new Recipe();
        spicyChickenRecipe.setDescription("Spicy Chicken");
        spicyChickenRecipe.setCookTime(10);
        spicyChickenRecipe.setPrepTime(25);
        spicyChickenRecipe.setDifficulty(Difficulty.MODERATE);
        spicyChickenRecipe.setDirections("Traditional hot chicken is made from whole chicken parts, marinated in a buttermilk and hot sauce brine, coated in a flour mixture, deep fried, and then slathered in a spicy red-hot paste.");

        Notes spicyChickenNotes = new Notes();
        spicyChickenNotes.setRecipeNotes("You can use chicken breasts for this recipe, but you should cut the chicken into 3 pieces. First lay the chicken breast horizontally, in a landscape position and then cut the chicken in half, perpendicular to you. Set aside the thinner side that tapers. Take the thicker pieces, " +
                "and cut those in half lengthwise, creating two thinner pieces. Repeat with the remaining pieces of chicken, creating 9 pieces total.");
        spicyChickenNotes.setRecipe(spicyChickenRecipe);
        spicyChickenRecipe.setNotes(spicyChickenNotes);

        spicyChickenRecipe.getIngredients().add(new Ingredient("all-purpose flour", new BigDecimal(4), cupUom, spicyChickenRecipe));
        spicyChickenRecipe.getIngredients().add(new Ingredient("cayenne pepper", new BigDecimal(1), tablespoonUom, spicyChickenRecipe));
        spicyChickenRecipe.getIngredients().add(new Ingredient("paprika", new BigDecimal(2), tablespoonUom, spicyChickenRecipe));

        spicyChickenRecipe.getCategories().add(americanCategory);

        recipes.add(spicyChickenRecipe);

        Recipe steakRecipe = new Recipe();
        steakRecipe.setDescription("Perfect Steak");
        steakRecipe.setCookTime(30);
        steakRecipe.setPrepTime(15);
        steakRecipe.setDifficulty(Difficulty.MODERATE);
        steakRecipe.setDirections("It is normally grilled or fried. Steak can be diced, cooked in sauce, such as in steak and kidney pie, or minced and formed into patties, such as hamburgers.");

        Notes steakNotes = new Notes();
        steakNotes.setRecipeNotes("A steak is a thick cut of meat generally sliced across the muscle fibers, sometimes including a bone.");
        steakNotes.setRecipe(steakRecipe);
        steakRecipe.setNotes(steakNotes);

        steakRecipe.getIngredients().add(new Ingredient("steak", new BigDecimal(2), eachUom, steakRecipe));
        steakRecipe.getIngredients().add(new Ingredient("Kosher salt ", new BigDecimal(5), teaspoonUom, steakRecipe));
        steakRecipe.getIngredients().add(new Ingredient("Kosher salt ", new BigDecimal(5), teaspoonUom, steakRecipe));

        steakRecipe.getCategories().add(americanCategory);

        recipes.add(steakRecipe);


        Recipe burgerRecipe = new Recipe();
        burgerRecipe.setDescription("Best Burger");
        burgerRecipe.setCookTime(15);
        burgerRecipe.setPrepTime(30);
        burgerRecipe.setDifficulty(Difficulty.EASY);
        burgerRecipe.setDirections("In a bowl, mix ground beef, egg, onion, bread crumbs, Worcestershire, garlic, 1/2 teaspoon salt, and 1/4 teaspoon pepper until well blended. Divide mixture into four equal portions and shape each into a patty about 4 inches wide.\n" +
                "\n");

        burgerRecipe.getIngredients().add(new Ingredient("beef", new BigDecimal(2), eachUom, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("minced onion", new BigDecimal(0.5), cupUom, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("Worcestershire", new BigDecimal(1), tablespoonUom, burgerRecipe));

        burgerRecipe.getCategories().add(americanCategory);

        recipes.add(burgerRecipe);

        return recipes;
    }
}
