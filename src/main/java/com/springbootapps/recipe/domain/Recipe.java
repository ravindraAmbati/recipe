package com.springbootapps.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer prepTime;
    private Integer cookingTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    // lob - larege object byte underlying object
    @Lob
    private Byte[] image;

    //cascade - Recipe is the owner of Notes
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        ingredients.forEach(ingredient -> {
            ingredient.setRecipe(this);
        });
        this.ingredients = ingredients;
    }

    public void setCategories(Set<Category> categories) {
        categories.forEach(category -> {
            HashSet<Recipe> recipeSet = new HashSet<Recipe>();
            recipeSet.add(this);
            category.setRecipes(recipeSet);
        });
        this.categories = categories;
    }
}
