package com.springbootapps.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@ToString(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Builder
    public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    // default fetch of OneToOne is Eager.
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public void setUom(UnitOfMeasure uom) {
        if (null != uom) {
            uom.setIngredient(this);
        }
        this.uom = uom;
    }
}

