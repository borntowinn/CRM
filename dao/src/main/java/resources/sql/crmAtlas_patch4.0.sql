DROP TABLE comments_to_company;
DROP TABLE comments_to_deal;
DROP TABLE comment_to_contact;
DROP TABLE comments_to_task;
DROP TABLE files_to_company;
DROP TABLE files_to_contact;
DROP TABLE files_to_deal;
DROP TABLE files_to_user;

ALTER TABLE comment ADD COLUMN company_id integer;
ALTER TABLE comment ADD COLUMN contact_id integer;
ALTER TABLE comment ADD COLUMN deal_id integer;
ALTER TABLE comment ADD COLUMN task_id integer;

ALTER TABLE file ADD COLUMN user_id integer;
ALTER TABLE file ADD COLUMN company_id integer;
ALTER TABLE file ADD COLUMN contact_id integer;
ALTER TABLE file ADD COLUMN deal_id integer;


