package com.springbootapps.recipe.controllers;

import com.springbootapps.recipe.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final RecipeRepository recipeRepository;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex(Model model) {
        log.info("called index");
        model.addAttribute("recipes", recipeRepository.findAll());
        recipeRepository.findAll().forEach(recipe -> log.info(recipe.toString()));
        return "index";
    }
}
