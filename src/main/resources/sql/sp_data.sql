DELIMITER $$
create procedure initData()
BEGIN
	IF (SELECT true FROM recipe.category LIMIT 1)
    THEN
		INSERT INTO recipe.category (description) VALUES ('American'),('Italian'),('Mexican'),('Fast Food');
        SELECT 'category table is updated with 4 rows';
	ELSE
		SELECT 'category table is updated with 0 rows';
	END IF;

	IF (SELECT true FROM recipe.unit_of_measure LIMIT 1)
    THEN
        INSERT INTO recipe.unit_of_measure (ingredient_id,description) VALUES  (1,'Teaspoon'),(2,'Tablespoon'),(3,'Cup'),(4,'Pinch'),(5,'Ounce'),(6,'Each'),(7,'Dash'),(8,'Pint');
		SELECT 'unit_of_measure table is updated with 8 rows';
	ELSE
		SELECT 'unit_of_measure table is updated with 0 rows';
	END IF;
END$$
DELIMITER ;