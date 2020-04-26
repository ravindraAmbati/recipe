package com.springbootapps.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Recipe {

    private Integer cookTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer prepTime;

    @Builder
    public Recipe(Long id, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, String description, Difficulty difficulty, Set<Ingredient> ingredients, Byte[] image, Notes notes, Set<Category> categories) {
        this.id = id;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.description = description;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.image = image;
        this.notes = notes;
        this.categories = categories;
    }
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
        if (null != notes) {
            notes.setRecipe(this);
        }
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

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
    }

    public void addCategory(Category category) {
        Set<Recipe> recipes = new HashSet<>(1);
        recipes.add(this);
        category.setRecipes(recipes);
        categories.add(category);
    }
}
