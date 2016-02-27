DROP TABLE IF EXISTS tags_to_company;
DROP TABLE IF EXISTS tags_to_contact;
DROP TABLE IF EXISTS tags_to_deal;

ALTER TABLE tag ADD COLUMN company_id integer;
ALTER TABLE tag ADD COLUMN contact_id integer;
ALTER TABLE tag ADD COLUMN deal_id integer;
