package com.springbootapps.recipe.repositories;

import com.springbootapps.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByDescription_Tablespoon() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        UnitOfMeasure expected = UnitOfMeasure.builder().description("Tablespoon").build();
        UnitOfMeasure actual = unitOfMeasureOptional.orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void findByDescription_Cup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");
        UnitOfMeasure expected = UnitOfMeasure.builder().description("Cup").build();
        UnitOfMeasure actual = unitOfMeasureOptional.orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
    }
}