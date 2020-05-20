DELIMITER $$
CREATE PROCEDURE createTables()
BEGIN
    create table if not exists recipe.category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
    create table if not exists recipe.ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, uom_id bigint, primary key (id)) engine=InnoDB;
    create table if not exists recipe.notes (id bigint not null auto_increment, recipe_notes longtext, recipe_id bigint, primary key (id)) engine=InnoDB;
    create table if not exists recipe.recipe (id bigint not null auto_increment, cook_time integer, description varchar(255), difficulty varchar(255), directions longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), notes_id bigint, primary key (id)) engine=InnoDB;
    create table if not exists recipe.recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB;
    create table if not exists recipe.unit_of_measure (id bigint not null auto_increment, ingredient_id bigint not null, description varchar(255), primary key (id)) engine=InnoDB;
END$$
DELIMITER ;