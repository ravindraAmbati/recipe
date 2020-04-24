package com.springbootapps.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
@ToString(exclude = {"recipes"})
@Entity
public class Category {

    @Builder
    public Category(Long id, String description, Set<Recipe> recipes) {
        this.id = id;
        this.description = description;
        this.recipes = recipes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
