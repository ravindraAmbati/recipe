package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.domain.Recipe;
import com.springbootapps.recipe.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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
        //given
        Set<Recipe> expected = new HashSet<>();
        expected.add(Recipe.builder().id(1L).build());
        expected.add(Recipe.builder().id(2L).build());

        //when
        when(recipeService.getRecipes()).thenReturn(expected);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //then
        assertEquals("index", testClass.getIndex(model));
        verify(recipeService, times(2)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> actual = argumentCaptor.getValue();
        assertEquals(expected.size(), actual.size());
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }
}