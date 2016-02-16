CREATE SEQUENCE files_to_contact_files_to_contact_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE files_to_contact ALTER COLUMN files_to_contact_id SET DEFAULT nextval('files_to_contact_files_to_contact_id_seq'::regclass);

