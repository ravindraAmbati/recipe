package com.springbootapps.recipe.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @After
    public void tearDown() {
        category = null;
    }

    @Test
    public void getId() {
        Long id = 1L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

}