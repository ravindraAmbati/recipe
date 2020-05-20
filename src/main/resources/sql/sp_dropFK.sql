DELIMITER $$
CREATE PROCEDURE dropFK()
BEGIN
	IF EXISTS
		(SELECT true FROM recipe.recipe_category LIMIT 1)
    THEN
		alter table recipe.recipe_category drop foreign key FKRC_C_ID;
		alter table recipe.recipe_category drop foreign key FKRC_R_ID;
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.recipe LIMIT 1)
    THEN
		alter table recipe.recipe drop foreign key FKR_N_ID;
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.notes LIMIT 1)
    THEN
		alter table recipe.notes drop foreign key FKN_R_ID;
	END IF;
    IF EXISTS
		(SELECT true FROM recipe.ingredient LIMIT 1)
    THEN
		alter table recipe.ingredient drop foreign key FKI_R_ID;
		alter table recipe.ingredient drop foreign key FKI_U_ID;
	END IF;
	IF EXISTS
		(SELECT true FROM recipe.unit_of_measure LIMIT 1)
    THEN
		alter table recipe.unit_of_measure drop foreign key FKU_I_ID;
	END IF;
END$$
DELIMITER ;