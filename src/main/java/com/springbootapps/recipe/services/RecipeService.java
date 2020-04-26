package com.springbootapps.recipe.services;


import com.springbootapps.recipe.commands.RecipeCommand;
import com.springbootapps.recipe.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
