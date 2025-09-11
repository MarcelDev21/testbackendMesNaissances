ALTER TABLE declarations
ADD COLUMN roles_id INT;

ALTER TABLE declarations
ADD CONSTRAINT fk_declarations_roles
FOREIGN KEY (roles_id) REFERENCES roles(id);
