package com.springbootapps.recipe.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"ingredient"})
@ToString(exclude = {"ingredient"})
@Entity
public class UnitOfMeasure {

    @Builder
    public UnitOfMeasure(Long id, String description, Ingredient ingredient) {
        this.id = id;
        this.description = description;
        this.ingredient = ingredient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    private Ingredient ingredient;
}
