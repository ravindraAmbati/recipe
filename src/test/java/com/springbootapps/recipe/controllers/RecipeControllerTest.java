package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.domain.Recipe;
import com.springbootapps.recipe.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    RecipeController testClass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        testClass = new RecipeController(recipeService);
    }

    @AfterEach
    void tearDown() {
        testClass = null;
    }

    @Test
    void showRecipe() {
        //given
        Long id = 1L;
        Recipe expected = Recipe.builder().id(id).build();

        //when
        when(recipeService.findById(id)).thenReturn(expected);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //then
        assertEquals("recipe/show", testClass.showById(id.toString(), model));
        verify(recipeService, times(2)).findById(id);
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        Recipe actual = argumentCaptor.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(testClass).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }
}