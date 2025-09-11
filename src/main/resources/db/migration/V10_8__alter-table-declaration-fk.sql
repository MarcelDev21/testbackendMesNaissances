
ALTER TABLE declarations
DROP FOREIGN KEY fk_declarations_company;

ALTER TABLE declarations
ADD CONSTRAINT fk_declarations_company
FOREIGN KEY (company_id) REFERENCES companies(id);
