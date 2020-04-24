package com.springbootapps.recipe.bootstrap;

import com.springbootapps.recipe.domain.*;
import com.springbootapps.recipe.repositories.CategoryRepository;
import com.springbootapps.recipe.repositories.RecipeRepository;
import com.springbootapps.recipe.repositories.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        HashSet<Category> categories = new HashSet<>(2);
        categories.add(categoryRepository.findByDescription("American").orElse(null));
        categories.add(categoryRepository.findByDescription("Italian").orElse(null));
        Ingredient ingredient1 = Ingredient.builder().description("Olive Oil").amount(new BigDecimal(1)).uom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null)).build();
        Ingredient ingredient2 = Ingredient.builder().description("green salad").amount(new BigDecimal(1)).uom(unitOfMeasureRepository.findByDescription("Bunch").orElse(null)).build();
        HashSet<Ingredient> ingredients = new HashSet<>(2);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        Notes notes = Notes.builder().recipeNotes("By Marianne TurnerMagazine subscription – 5 issues for £5").build();
        Recipe recipe1 = Recipe.builder()
                .categories(categories)
                .cookingTime(25)
                .prepTime(10)
                .difficulty(Difficulty.EASY)
                .servings(6)
                .description("Upgrade cheesy tomato pasta with gnocchi, chorizo and mozzarella for a comforting bake that makes an excellent midweek meal")
                .directions("Heat the oil in a medium pan over a medium heat. Fry the onion and garlic for 8-10 mins until soft. Add the chorizo and fry for 5 mins more. Tip in the tomatoes and sugar, and season. Bring to a simmer, then add the gnocchi and cook for 8 mins, stirring often, until soft. Heat the grill to high." +
                        "Stir ¾ of the mozzarella and most of the basil through the gnocchi. Divide the mixture between six ovenproof ramekins, or put in one baking dish. Top with the remaining mozzarella, then grill for 3 mins, or until the cheese is melted and golden. Season, scatter over the remaining basil and serve with green salad.")
                .ingredients(ingredients)
                .source("bbcgoodfood")
                .url("https://www.bbcgoodfood.com/recipes/chorizo-mozzarella-gnocchi-bake")
                .notes(notes)
                .build();
        recipes.add(recipe1);
        return recipes;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.info("Successfully saved recipes");
        recipeRepository.findAll().forEach(recipe -> log.info(recipe.toString()));
    }
}
