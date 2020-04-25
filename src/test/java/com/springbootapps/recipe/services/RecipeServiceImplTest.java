package com.springbootapps.recipe.services;

import com.springbootapps.recipe.domain.Recipe;
import com.springbootapps.recipe.repositories.RecipeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeService recipeService;
    @Mock
    private RecipeRepository recipeRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @After
    public void tearDown() {
        recipeService = null;
    }

    @Test
    public void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(recipes.size(), recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }
}