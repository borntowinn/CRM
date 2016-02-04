ALTER TABLE deal ADD COLUMN name character varying(50);
UPDATE deal SET name = 'test name';