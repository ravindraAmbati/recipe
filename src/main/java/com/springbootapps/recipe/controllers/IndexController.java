package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.domain.Category;
import com.springbootapps.recipe.domain.UnitOfMeasure;
import com.springbootapps.recipe.repositories.CategoryRepository;
import com.springbootapps.recipe.repositories.RecipeRepository;
import com.springbootapps.recipe.repositories.UnitOfMeasureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    @NonNull
    private final CategoryRepository categoryRepository;
    @NonNull
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    @NonNull
    private final RecipeRepository recipeRepository;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Indian");
        categoryOptional.ifPresent(category -> log.info(category.toString()));
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");
        unitOfMeasureOptional.ifPresent(unitOfMeasure -> log.info(unitOfMeasure.toString()));
        return "index";
    }
}
