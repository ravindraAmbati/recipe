package com.springbootapps.recipe.services;

import com.springbootapps.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
