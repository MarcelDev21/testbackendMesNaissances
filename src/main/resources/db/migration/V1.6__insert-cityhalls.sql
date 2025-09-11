INSERT INTO cityhalls(`name`, `address_id`)
VALUES
("New York City Hall", (SELECT id FROM address WHERE tag = 'city-hall' LIMIT 1)),
("Tokyio Ruse", (SELECT id FROM address WHERE tag = 'city-hall' LIMIT 1));
