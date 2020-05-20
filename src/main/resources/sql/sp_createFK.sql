DELIMITER $$
CREATE PROCEDURE createFK()
BEGIN
	IF EXISTS
		(SELECT true FROM recipe.recipe_category LIMIT 1) AND
        (SELECT true FROM recipe.category LIMIT 1) AND
        (SELECT true FROM recipe.recipe LIMIT 1)
    THEN
		alter table recipe_category add constraint FKRC_C_ID foreign key (category_id) references category (id);
		alter table recipe_category add constraint FKRC_R_ID foreign key (recipe_id) references recipe (id);
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.recipe LIMIT 1) AND
        (SELECT true FROM recipe.notes LIMIT 1)
    THEN
		alter table recipe add constraint FKR_N_ID foreign key (notes_id) references notes (id);
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.notes LIMIT 1) AND
        (SELECT true FROM recipe.recipe LIMIT 1)
    THEN
		alter table notes add constraint FKN_R_ID foreign key (recipe_id) references recipe (id);
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.ingredient LIMIT 1) AND
        (SELECT true FROM recipe.recipe LIMIT 1) AND
        (SELECT true FROM recipe.unit_of_measure LIMIT 1)
    THEN
		alter table ingredient add constraint FKI_R_ID foreign key (recipe_id) references recipe (id);
		alter table ingredient add constraint FKI_U_ID foreign key (uom_id) references unit_of_measure (id);
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.ingredient LIMIT 1) AND
        (SELECT true FROM recipe.unit_of_measure LIMIT 1)
    THEN
		alter table unit_of_measure add constraint FKU_I_ID foreign key (ingredient_id) references ingredient (id);
	END IF;
END$$
DELIMITER ;
