package com.springbootapps.recipe.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @AfterEach
    void tearDown() {
        category = null;
    }

    @Test
    void getId() {
        Long id = 1L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

}