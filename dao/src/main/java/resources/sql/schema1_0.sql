-- tables
-- Table: currency
CREATE TABLE currency (
    currency_id SERIAL  NOT NULL,
    currency varchar(50)  NOT NULL,
    CONSTRAINT currency_pk PRIMARY KEY (currency_id)
);

-- Table: timezone
CREATE TABLE timezone (
    timezone_id SERIAL  NOT NULL,
    timezone varchar(250)  NOT NULL,
    CONSTRAINT timezone_pk PRIMARY KEY (timezone_id)
);


-- Table: comment
CREATE TABLE comment (
    comment_id SERIAL  NOT NULL,
    comment varchar(500)  NOT NULL,
    data_creation timestamp,
    CONSTRAINT comment_pk PRIMARY KEY (comment_id)
);



-- Table: comment_to_contact
CREATE TABLE comment_to_contact (
    comment_to_contact_id SERIAL  NOT NULL,
    comment_id int  NOT NULL,
    contact_id int  NOT NULL,
    CONSTRAINT comment_to_contact_pk PRIMARY KEY (comment_to_contact_id)
);



-- Table: comments_to_company
CREATE TABLE comments_to_company (
    comments_to_company_id SERIAL  NOT NULL,
    comment_id int  NOT NULL,
    company_id int  NOT NULL,
    CONSTRAINT comments_to_company_pk PRIMARY KEY (comments_to_company_id)
);



-- Table: comments_to_deal
CREATE TABLE comments_to_deal (
    comments_to_deal_id SERIAL  NOT NULL,
    comment_id int  NOT NULL,
    deal_id int  NOT NULL,
    CONSTRAINT comments_to_deal_pk PRIMARY KEY (comments_to_deal_id)
);


-- Table: company
CREATE TABLE company (
    company_id SERIAL  NOT NULL,
    company_name varchar(25)  NOT NULL,
    phone_type int,
    phone_number varchar(13),
    email varchar(50),
    web_site varchar(150),
    createdBy int,
    address varchar(250),
    isDeleted bool,
    creation_time timestamp,
    CONSTRAINT company_pk PRIMARY KEY (company_id)
);



-- Table: contact
CREATE TABLE contact (
    contact_id SERIAL  NOT NULL,
    name_surname varchar(50),
    phone_type int,
    phone_number varchar(50),
    email varchar(100),
    skype varchar(100),
    position varchar(30),
    isDeleted bool,
    creation_time timestamp,
    createdBy int  NOT NULL,
    company_id int,
    CONSTRAINT contact_pk PRIMARY KEY (contact_id)
);



-- Table: deal
CREATE TABLE deal (
    deal_id SERIAL  NOT NULL,
    createdBy int  NOT NULL,
    budget decimal(15,2)  NOT NULL,
    phase_id int,
    responsible int  NOT NULL,
    date_creation timestamp,
    company_id int,
    contact_id int,
    isDeleted bool,
    CONSTRAINT deal_pk PRIMARY KEY (deal_id)
);



-- Table: phase
CREATE TABLE phase (
    phase_id SERIAL  NOT NULL,
    phase varchar(50),
    CONSTRAINT phase_id_pk PRIMARY KEY (phase_id)
);


-- Table: file
CREATE TABLE file (
    file_id SERIAL  NOT NULL,
    date_creation timestamp,
    file bytea  NOT NULL,
    file_name varchar(150)  NOT NULL,
    CONSTRAINT file_pk PRIMARY KEY (file_id)
);



-- Table: files_to_company
CREATE TABLE files_to_company (
    files_to_company_id SERIAL  NOT NULL,
    file_id int  NOT NULL,
    company_id int  NOT NULL,
    CONSTRAINT files_to_company_pk PRIMARY KEY (files_to_company_id)
);



-- Table: files_to_contact
CREATE TABLE files_to_contact (
    files_to_contact_id int  NOT NULL,
    file_id int  NOT NULL,
    contact_id int  NOT NULL,
    CONSTRAINT files_to_contact_pk PRIMARY KEY (files_to_contact_id)
);



-- Table: files_to_deal
CREATE TABLE files_to_deal (
    files_to_deal_id SERIAL  NOT NULL,
    file_id int  NOT NULL,
    deal_id int  NOT NULL,
    CONSTRAINT files_to_deal_pk PRIMARY KEY (files_to_deal_id)
);



-- Table: files_to_user
CREATE TABLE files_to_user (
    files_to_user_id SERIAL  NOT NULL,
    file_id int  NOT NULL,
    user_id int  NOT NULL,
    CONSTRAINT files_to_user_pk PRIMARY KEY (files_to_user_id)
);



-- Table: tag
CREATE TABLE tag (
    tag_id SERIAL  NOT NULL,
    tag varchar(50)  NOT NULL,
    CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);



-- Table: tags_to_company
CREATE TABLE tags_to_company (
    tags_to_company_id SERIAL  NOT NULL,
    company_id int  NOT NULL,
    tag_id int  NOT NULL,
    CONSTRAINT tags_to_company_pk PRIMARY KEY (tags_to_company_id)
);



-- Table: tags_to_contact
CREATE TABLE tags_to_contact (
    tags_to_contact_id SERIAL  NOT NULL,
    contact_id int  NOT NULL,
    tag_id int  NOT NULL,
    CONSTRAINT tags_to_contact_pk PRIMARY KEY (tags_to_contact_id)
);



-- Table: tags_to_deal
CREATE TABLE tags_to_deal (
    tags_to_deal_id SERIAL  NOT NULL,
    tag_id int  NOT NULL,
    deal_id int  NOT NULL,
    CONSTRAINT tags_to_deal_pk PRIMARY KEY (tags_to_deal_id)
);



-- Table: task
CREATE TABLE task (
    task_id SERIAL  NOT NULL,
    period varchar(50),
    task_name varchar(50),
    plantime time,
    responsible int NOT NULL,
    task_type varchar(50),
    author int NOT NULL,
    company_id int,
    deal_id int,
    creation_time timestamp,
    contact_id int,
    isDeleted bool,
    CONSTRAINT task_pk PRIMARY KEY (task_id)
);



-- Table: "user"
CREATE TABLE "user" (
    user_id SERIAL  NOT NULL,
    name varchar(50)  NOT NULL,
    password varchar(50)  NOT NULL,
    description varchar(250),
    date_creation timestamp,
    email varchar(50),
    mobile_phone varchar(50),
    work_phone varchar(50),
    user_role_id int  NOT NULL,
    language int,
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);

-- Table: session_history
CREATE TABLE session_history (
    session_history_id SERIAL  NOT NULL,
    user_id int,
    ip_address varchar(50),
    browser varchar(50),
    data_session timestamp,
    CONSTRAINT session_history_pk PRIMARY KEY (session_history_id)
);

-- Table: user_role
CREATE TABLE user_role (
    user_role_id SERIAL  NOT NULL,
    role varchar(50)  NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (user_role_id)
);



-- Table: comments_to_task
CREATE TABLE comments_to_task (
    comments_to_task_id SERIAL  NOT NULL,
    comment_id int  NOT NULL,
    task_id int  NOT NULL,
    CONSTRAINT comments_to_task_pk PRIMARY KEY (comments_to_task_id)
);







-- foreign keys
-- Reference:  Comment_To_Deal_comment (table: comments_to_deal)

ALTER TABLE comments_to_deal ADD CONSTRAINT Comment_To_Deal_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Comment_To_Deal_deal (table: comments_to_deal)

ALTER TABLE comments_to_deal ADD CONSTRAINT Comment_To_Deal_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Contact_To_Comment_comment (table: comment_to_contact)

ALTER TABLE comment_to_contact ADD CONSTRAINT Contact_To_Comment_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Contact_To_Comment_contact (table: comment_to_contact)

ALTER TABLE comment_to_contact ADD CONSTRAINT Contact_To_Comment_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Contact_to_File_contact (table: files_to_contact)

ALTER TABLE files_to_contact ADD CONSTRAINT Contact_to_File_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Contact_to_File_file (table: files_to_contact)

ALTER TABLE files_to_contact ADD CONSTRAINT Contact_to_File_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Files_To_Companies_company (table: files_to_company)

ALTER TABLE files_to_company ADD CONSTRAINT Files_To_Companies_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Files_To_Companies_file (table: files_to_company)

ALTER TABLE files_to_company ADD CONSTRAINT Files_To_Companies_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Files_To_Deals_deal (table: files_to_deal)

ALTER TABLE files_to_deal ADD CONSTRAINT Files_To_Deals_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Files_To_Deals_file (table: files_to_deal)

ALTER TABLE files_to_deal ADD CONSTRAINT Files_To_Deals_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Tags_To_Contacts_tag (table: tags_to_contact)

ALTER TABLE tags_to_contact ADD CONSTRAINT Tags_To_Contacts_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Tags_To_Contacts_contact (table: tags_to_contact)

ALTER TABLE tags_to_contact ADD CONSTRAINT Tags_To_Contacts_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Tags_To_Deals_deal (table: tags_to_deal)

ALTER TABLE tags_to_deal ADD CONSTRAINT Tags_To_Deals_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Tags_To_Deals_tag (table: tags_to_deal)

ALTER TABLE tags_to_deal ADD CONSTRAINT Tags_To_Deals_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Task_To_Comment_comment (table: comments_to_task)

ALTER TABLE comments_to_task ADD CONSTRAINT Task_To_Comment_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Task_To_Comment_task (table: comments_to_task)

ALTER TABLE comments_to_task ADD CONSTRAINT Task_To_Comment_task
    FOREIGN KEY (task_id)
    REFERENCES task (task_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  User_To_File_file (table: files_to_user)

ALTER TABLE files_to_user ADD CONSTRAINT User_To_File_file
    FOREIGN KEY (file_id)
    REFERENCES file (file_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  User_To_File_user (table: files_to_user)

ALTER TABLE files_to_user ADD CONSTRAINT User_To_File_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  comments_to_company_comment (table: comments_to_company)

ALTER TABLE comments_to_company ADD CONSTRAINT comments_to_company_comment
    FOREIGN KEY (comment_id)
    REFERENCES comment (comment_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  comments_to_company_company (table: comments_to_company)

ALTER TABLE comments_to_company ADD CONSTRAINT comments_to_company_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_to_tag_tag (table: tags_to_company)

ALTER TABLE tags_to_company ADD CONSTRAINT company_to_tag_tag
    FOREIGN KEY (tag_id)
    REFERENCES tag (tag_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_to_tag_tag (table: tags_to_company)

ALTER TABLE tags_to_company ADD CONSTRAINT company_to_tag_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  company_user (table: company)

ALTER TABLE company ADD CONSTRAINT company_user
    FOREIGN KEY (createdBy)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_company (table: contact)

ALTER TABLE contact ADD CONSTRAINT contact_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  contact_user (table: contact)

ALTER TABLE contact ADD CONSTRAINT contact_user
    FOREIGN KEY (createdBy)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_company (table: deal)

ALTER TABLE deal ADD CONSTRAINT deal_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_user (table: deal)

ALTER TABLE deal ADD CONSTRAINT deal_user
    FOREIGN KEY (createdBy)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_user (table: deal)

ALTER TABLE deal ADD CONSTRAINT deal_user_responsible
    FOREIGN KEY (responsible)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_company (table: task)

ALTER TABLE task ADD CONSTRAINT task_company
    FOREIGN KEY (company_id)
    REFERENCES company (company_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_contact (table: task)

ALTER TABLE task ADD CONSTRAINT task_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_deal (table: task)

ALTER TABLE task ADD CONSTRAINT task_deal
    FOREIGN KEY (deal_id)
    REFERENCES deal (deal_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_user (table: task)

ALTER TABLE task ADD CONSTRAINT task_user
    FOREIGN KEY (responsible)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  task_user (table: task)

ALTER TABLE task ADD CONSTRAINT task_user_author
    FOREIGN KEY (author)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  user_user_role (table: "user")

ALTER TABLE "user" ADD CONSTRAINT user_user_role
    FOREIGN KEY (user_role_id)
    REFERENCES user_role (user_role_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  session_history_user (table: session_history)

ALTER TABLE session_history ADD CONSTRAINT session_history_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (user_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_phase (table: deal)

ALTER TABLE deal ADD CONSTRAINT deal_phase
    FOREIGN KEY (phase_id)
    REFERENCES phase (phase_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  deal_contact (table: deal)

ALTER TABLE deal ADD CONSTRAINT deal_contact
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;
