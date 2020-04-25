package com.springbootapps.recipe.repositories;

import com.springbootapps.recipe.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription_Tablespoon() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        UnitOfMeasure expected = UnitOfMeasure.builder().description("Tablespoon").build();
        UnitOfMeasure actual = unitOfMeasureOptional.orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void findByDescription_Cup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");
        UnitOfMeasure expected = UnitOfMeasure.builder().description("Cup").build();
        UnitOfMeasure actual = unitOfMeasureOptional.orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void findByDescription_Ounce() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Ounce");
        UnitOfMeasure expected = UnitOfMeasure.builder().description("Ounce").build();
        UnitOfMeasure actual = unitOfMeasureOptional.orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getDescription(), actual.getDescription());
    }
}