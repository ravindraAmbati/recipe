package com.springbootapps.recipe.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@ToString(exclude = {"recipe"})
@Entity
public class Notes {

    @Builder
    public Notes(Long id, Recipe recipe, String recipeNotes) {
        this.id = id;
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    // large object un-limit the string value
    @Lob
    private String recipeNotes;
}
