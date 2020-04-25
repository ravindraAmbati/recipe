package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.domain.Recipe;
import com.springbootapps.recipe.services.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController testClass;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testClass = new IndexController(recipeService);
    }

    @After
    public void tearDown() {
        testClass = null;
    }

    @Test
    public void getIndex() {
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

    @Test
    public void testMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(testClass).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}