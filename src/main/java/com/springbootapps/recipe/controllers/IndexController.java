package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.domain.Category;
import com.springbootapps.recipe.domain.UnitOfMeasure;
import com.springbootapps.recipe.repositories.CategoryRepository;
import com.springbootapps.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Indian");
        System.out.println(categoryOptional.get().toString());
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");
        System.out.println(unitOfMeasureOptional.get().toString());
        return "index";
    }
}
