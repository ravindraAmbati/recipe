package com.springbootapps.recipe.bootstrap;

import com.springbootapps.recipe.domain.*;
import com.springbootapps.recipe.repositories.CategoryRepository;
import com.springbootapps.recipe.repositories.RecipeRepository;
import com.springbootapps.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        Recipe recipe1 = new Recipe();
        Optional<Category> categoryOptional1 = categoryRepository.findByDescription("American");
        Optional<Category> categoryOptional2 = categoryRepository.findByDescription("Italian");
        HashSet<Category> categories = new HashSet<>(2);
        categories.add(categoryOptional1.get());
        categories.add(categoryOptional2.get());
        recipe1.setCategories(categories);
        recipe1.setCookingTime(25);
        recipe1.setPrepTime(10);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setServings(6);
        recipe1.setDescription("Upgrade cheesy tomato pasta with gnocchi, chorizo and mozzarella for a comforting bake that makes an excellent midweek meal");
        recipe1.setDirections("Heat the oil in a medium pan over a medium heat. Fry the onion and garlic for 8-10 mins until soft. Add the chorizo and fry for 5 mins more. Tip in the tomatoes and sugar, and season. Bring to a simmer, then add the gnocchi and cook for 8 mins, stirring often, until soft. Heat the grill to high." +
                "Stir ¾ of the mozzarella and most of the basil through the gnocchi. Divide the mixture between six ovenproof ramekins, or put in one baking dish. Top with the remaining mozzarella, then grill for 3 mins, or until the cheese is melted and golden. Season, scatter over the remaining basil and serve with green salad.");

        Optional<UnitOfMeasure> unitOfMeasureOptional1 = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> unitOfMeasureOptional2 = unitOfMeasureRepository.findByDescription("Bunch");
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("Olive Oil");
        ingredient1.setAmount(new BigDecimal(1));
        ingredient1.setUom(unitOfMeasureOptional1.get());
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("green salad");
        ingredient2.setAmount(new BigDecimal(1));
        ingredient2.setUom(unitOfMeasureOptional2.get());
        HashSet<Ingredient> ingredients = new HashSet<>(2);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        recipe1.setIngredients(ingredients);
        recipe1.setSource("bbcgoodfood");
        recipe1.setUrl("https://www.bbcgoodfood.com/recipes/chorizo-mozzarella-gnocchi-bake");
        recipe1.setNotes(new Notes(recipe1, "By Marianne TurnerMagazine subscription – 5 issues for £5"));
        recipes.add(recipe1);
        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
