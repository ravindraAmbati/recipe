package com.springbootapps.recipe.repositories;

/* @author ravin @date 20-05-2020 @time 17:10 */

import com.springbootapps.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
