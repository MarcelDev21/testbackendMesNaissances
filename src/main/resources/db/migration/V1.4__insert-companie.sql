INSERT INTO companies(`name`, `address_id`)
VALUES
("New York City Hall", (SELECT id FROM address WHERE tag = 'company' LIMIT 1)),
("Tokyio Ruse", (SELECT id FROM address WHERE tag = 'company' LIMIT 1));
