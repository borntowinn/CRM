ALTER TABLE deal ADD COLUMN name character varying(50);
UPDATE deal SET name = 'test name';

ALTER TABLE task ALTER COLUMN plantime TYPE timestamp USING ('2000-1-1'::timestamp without time zone);