ALTER TABLE contact ADD COLUMN responsible integer;
ALTER TABLE contact ADD FOREIGN KEY (responsible) REFERENCES "user"(user_id);
UPDATE contact SET responsible = 1;

ALTER TABLE company ADD COLUMN responsible integer;
ALTER TABLE company ADD FOREIGN KEY (responsible) REFERENCES "user"(user_id);
UPDATE company SET responsible = 1;

ALTER TABLE company DROP COLUMN phone_type;