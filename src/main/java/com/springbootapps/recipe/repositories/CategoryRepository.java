package com.springbootapps.recipe.repositories;

import com.springbootapps.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
