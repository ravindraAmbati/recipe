DELIMITER $$
CREATE PROCEDURE dropTables()
BEGIN
    drop table if exists recipe.unit_of_measure;
    drop table if exists recipe.recipe_category;
    drop table if exists recipe.recipe;
    drop table if exists recipe.notes;
    drop table if exists recipe.ingredient;
    drop table if exists recipe.category;
END$$
DELIMITER ;