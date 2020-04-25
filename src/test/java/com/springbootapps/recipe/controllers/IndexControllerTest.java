package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController testClass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        testClass = new IndexController(recipeService);
    }

    @AfterEach
    void tearDown() {
        testClass = null;
    }

    @Test
    void getIndex() {
        String viewName = testClass.getIndex(model);
        assertEquals("index", viewName);
        verify(recipeService, times(2)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}