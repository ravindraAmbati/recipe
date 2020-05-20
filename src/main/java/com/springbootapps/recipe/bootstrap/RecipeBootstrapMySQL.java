package com.springbootapps.recipe.bootstrap;

/* @author ravin @date 20-05-2020 @time 16:38 */

import com.springbootapps.recipe.domain.*;
import com.springbootapps.recipe.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Profile("mysql")
public class RecipeBootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final NotesRepository notesRepository;

    public RecipeBootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, NotesRepository notesRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.notesRepository = notesRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadRecipes();
    }

    private void loadRecipes() {

        log.info("loading Unit of categories");

        if (0L == categoryRepository.count()) {
            log.info("NO RECORDS");
        } else {
            categoryRepository.deleteAll();
        }

        Category american = new Category();
        american.setDescription("American");
        categoryRepository.save(american);

        Category italian = new Category();
        italian.setDescription("Italian");
        categoryRepository.save(italian);

        Category mexican = new Category();
        mexican.setDescription("Mexican");
        categoryRepository.save(mexican);

        Category fastFood = new Category();
        fastFood.setDescription("Fast Food");
        categoryRepository.save(fastFood);

        categoryRepository.findAll().forEach(category -> log.info(category.toString()));

        log.info("loading Unit of measures");

        if (0L == unitOfMeasureRepository.count()) {
            log.info("NO RECORDS");
        } else {
            unitOfMeasureRepository.deleteAll();
        }

        if (0L == ingredientRepository.count()) {
            log.info("NO RECORDS");
        } else {
            ingredientRepository.deleteAll();
        }

        if (0L == notesRepository.count()) {
            log.info("NO RECORDS");
        } else {
            notesRepository.deleteAll();
        }

        if (0L == recipeRepository.count()) {
            log.info("NO RECORDS");
        } else {
            recipeRepository.deleteAll();
        }

        UnitOfMeasure teaspoon = new UnitOfMeasure();
        teaspoon.setDescription("Teaspoon");
        teaspoon.setIngredient(new Ingredient());

        UnitOfMeasure tablespoon = new UnitOfMeasure();
        tablespoon.setDescription("Tablespoon");

        UnitOfMeasure cup = new UnitOfMeasure();
        cup.setDescription("Cup");

        UnitOfMeasure pinch = new UnitOfMeasure();
        pinch.setDescription("Pinch");


        Ingredient i1 = new Ingredient("ripe avocados", new BigDecimal(2), teaspoon);
        i1.setUom(teaspoon);
        Ingredient i2 = new Ingredient("Kosher salt", new BigDecimal(".5"), tablespoon);
        i2.setUom(tablespoon);
        Ingredient i3 = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), pinch);
        i3.setUom(pinch);
        Ingredient i4 = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), teaspoon);
        i4.setUom(teaspoon);
        Ingredient i5 = new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), tablespoon);
        i5.setUom(tablespoon);
        Ingredient i6 = new Ingredient("Cilantro", new BigDecimal(2), cup);
        i6.setUom(cup);
        Ingredient i7 = new Ingredient("freshly grated black pepper", new BigDecimal(2), pinch);
        i7.setUom(pinch);
        Ingredient i8 = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), cup);
        i8.setUom(cup);

//        ingredientRepository.save(i1);
//        ingredientRepository.save(i2);
//        ingredientRepository.save(i3);
//        ingredientRepository.save(i4);
//        ingredientRepository.save(i5);
//        ingredientRepository.save(i6);
//        ingredientRepository.save(i7);
//        ingredientRepository.save(i8);
//
//        unitOfMeasureRepository.save(teaspoon);
//        unitOfMeasureRepository.save(tablespoon);
//        unitOfMeasureRepository.save(cup);
//        unitOfMeasureRepository.save(pinch);
//        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> log.info(unitOfMeasure.toString()));

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);
//        notesRepository.save(guacNotes);

        //very redundent - could add helper method, and make this simpler
        guacRecipe.addIngredient(i1);
        guacRecipe.addIngredient(i2);
        guacRecipe.addIngredient(i3);
        guacRecipe.addIngredient(i4);
        guacRecipe.addIngredient(i5);
        guacRecipe.addIngredient(i6);
        guacRecipe.addIngredient(i7);
        guacRecipe.addIngredient(i8);

        guacRecipe.getCategories().add(american);
        guacRecipe.getCategories().add(mexican);

        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");
        recipeRepository.save(guacRecipe);
    }
}
