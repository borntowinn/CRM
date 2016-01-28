INSERT INTO user_role VALUES (1, 'admin');
INSERT INTO user_role VALUES (2, 'slave');

INSERT INTO currency VALUES (980, 'UAH');
INSERT INTO currency VALUES (840, 'USD');
INSERT INTO currency VALUES (978, 'EUR');

INSERT INTO timezone VALUES (1, 'Europe/Kiev');
INSERT INTO timezone VALUES (2, 'Europe/Warsaw');
INSERT INTO timezone VALUES (3, 'Europe/Berlin');

INSERT INTO "user" VALUES (1, 'Travis ', 'Kalanick', 'password', '1999-12-25 00:00:00', 'travis@uber.com', '+38-288-88-88', '+44-547-11-33', 2, 1);
INSERT INTO "user" VALUES (2, 'Elon', 'Musk', 'pass', '1999-12-25 00:00:00', 'musk@tesla.com', '+48-444-22-34', '+44-546-22-21', 2,1);
INSERT INTO "user" VALUES (3, 'Larry', 'Ellison', 'passwd', '1999-12-25 00:00:00', 'ellison@oracle.com', '+7-222-44-55', '+44-236-22-21', 2,1);
INSERT INTO "user" VALUES (4, 'Sergey', 'Brin', 'password1989', '1999-12-25 00:00:00', 'brin@gmail.com', '+6-222-56-32', '+44-543-33-22', 2,1);
INSERT INTO "user" VALUES (5, 'David', 'Karp', 'wordpass', '1999-12-25 00:00:00', 'karp@tumblr.com', '+44-555-33-11', '+32-222-45-67', 2,1);

INSERT INTO session_history VALUES (1,1, '123.321.123', 'chrome', '2015-12-25 00:00:00');
INSERT INTO session_history VALUES (2,1, '125.521.323', 'chrome', '2014-12-25 00:00:00');
INSERT INTO session_history VALUES (3,1, '126.621.153', 'chrome', '2013-12-25 00:00:00');

INSERT INTO comment VALUES (1, 'How many cares one loses when one decides not to be something but to be someone.', '2015-08-08');
INSERT INTO comment VALUES (3, 'Imitation is suicide', '2014-08-31');
INSERT INTO comment VALUES (4, 'Do your own thing on your own terms and get what you came here for', '2015-12-28');
INSERT INTO comment VALUES (2, 'Be who you are and say what you feel, because those who mind don’t matter and those who matter d not', '2015-08-09');
INSERT INTO comment VALUES (5, 'Do what you feel in your heart to be right, for you will be criticized anyway', '2016-01-01');


INSERT INTO company VALUES (1, 'Apple', 1, '1 (408) 996–1', 'apple@apple.com', 'www.apple.com', 5, '1 Infinite Loop, Cupertino, CA 95014', false, '1999-12-25 00:00:00');
INSERT INTO company VALUES (2, 'Amazon', 2, '1 (206) 266-1', 'amazon@amazon.com', 'www.amazon.com', 4, '410 Terry Ave. North, Seattle, WA,98109-5210', false, '1999-12-25 00:00:00');
INSERT INTO company VALUES (3, 'Google', 3, '1 (650) 253-0', 'google@gmail.com', 'www.google.com', 3, '1600 Amphitheatre Pkwy, Mountain View, CA 94043', false, '1999-12-25 00:00:00');
INSERT INTO company VALUES (4, 'Twitter', 1, '1 (605) 238-8', 'twitter@twitter.com', 'www.twitter.com', 2, '1355 Market Street Suite 900, San Francisco, CA 94103', false, '1999-12-25 00:00:00');
INSERT INTO company VALUES (5, 'Uber', 2, '1 (877) 223-8', 'support@uber.com', 'www.uber.com', 1, '1455 Market St 4th Fl, San Francisco, CA ', false, '1999-12-25 00:00:00');

INSERT INTO contact VALUES (1, 'Martin', 1, '+38-248-88-99', 'king@gmail.com', 'martin.king', 'CEO', false, '2011-11-09 00:00:00', 2, 3);
INSERT INTO contact VALUES (2, 'Lora', 2, '+48-222-56-66', 'fauler@yahoo.com', 'lorochka', 'finance manager', false, '2012-11-08 00:00:00', 3, 1);
INSERT INTO contact VALUES (3, 'Marat', 3, '+1-555-0000', 'farat@mail.ru', 'marat.farat', 'project manager', false, '2013-08-09 00:00:00', 1, 5);
INSERT INTO contact VALUES (4, 'Jesus', 3, '+6-222-66-55', 'jesus@gmail.com', 'jesus.christ', 'unemployed', true, '1999-12-25 00:00:00', 1, 4);
INSERT INTO contact VALUES (5, 'Larry', 1, '+77-22-33-44', 'page@gmail.com', 'larry.page', 'CTO', false, '2001-12-12 00:00:00', 2, 3);


INSERT INTO phase VALUES (1, 'Initial contact');
INSERT INTO phase VALUES (2, 'Negotiation');
INSERT INTO phase VALUES (3, 'Decision taking');

INSERT INTO deal VALUES (1, 5, 1000000.00, 2, 2, '2016-01-22 00:00:00', 2, 2, false);
INSERT INTO deal VALUES (2, 4, 2000000000.00, 1, 1, '2015-12-12 00:00:00', 1, 2, false);
INSERT INTO deal VALUES (3, 3, 150000.00, 3, 3, '2014-01-01 00:00:00', 5, 2, false);
INSERT INTO deal VALUES (4, 2, 800.99, 1, 1, '2016-08-07 00:00:00', 5, 2, false);
INSERT INTO deal VALUES (5, 1, 600000.00, 2, 2, '2014-12-31 00:00:00', 4, 2, false);

INSERT INTO file VALUES (1, '2016-08-12 00:00:00', '\x013d7d16d7ad4fefb61bd95b765c8ceb', 'flower.png');
INSERT INTO file VALUES (2, '2015-08-12 00:00:00', '\x013d7d16d7ad4fefb61bd95b765c8ceb', 'flower.png');
INSERT INTO file VALUES (3, '2016-09-12 00:00:00', '\x013d7d16d7ad4fefb61bd95b765c8ceb', 'doc.pdf');
INSERT INTO file VALUES (4, '2016-08-12 00:00:00', '\x013d7d16d7ad4fefb61bd95b765c8ceb', 'wire.doc');
INSERT INTO file VALUES (5, '2016-08-11 00:00:00', '\x013d7d16d7ad4fefb61bd95b765c8ceb', 'java.png');

INSERT INTO tag VALUES (1, 'important');
INSERT INTO tag VALUES (2, 'busy');
INSERT INTO tag VALUES (3, 'dead');
INSERT INTO tag VALUES (4, 'success');
INSERT INTO tag VALUES (5, 'fail');

INSERT INTO task VALUES (1, 'today', 'task1', '12:00:00', 2, 'call',  3, 4, 5, '2016-08-09 00:00:00', 5, false);
INSERT INTO task VALUES (2, 'tomorrow', 'task2', '17:00:00', 1, 'follow-up', 4, 5, 5, '2015-01-01 00:00:00', 4, false);
INSERT INTO task VALUES (3, 'all day', 'task3', '13:00:00', 3, 'other', 4, 3, 3,  '2015-12-31 00:00:00', 2, false);
INSERT INTO task VALUES (4, 'next week', 'task4', '12:00:00', 2, 'meeting', 2, 1, 4, '2013-12-08 00:00:00', 1, false);
INSERT INTO task VALUES (5, 'next year', 'task5', '11:00:00', 5, 'call', 1, 2,5,  '2015-02-03 00:00:00', 3, false);

INSERT INTO comment_to_contact VALUES (1, 1, 5);
INSERT INTO comment_to_contact VALUES (2, 2, 4);
INSERT INTO comment_to_contact VALUES (3, 3, 3);
INSERT INTO comment_to_contact VALUES (4, 4, 2);
INSERT INTO comment_to_contact VALUES (5, 5, 1);

INSERT INTO comments_to_company VALUES (1, 5, 1);
INSERT INTO comments_to_company VALUES (2, 4, 2);
INSERT INTO comments_to_company VALUES (3, 3, 3);
INSERT INTO comments_to_company VALUES (4, 2, 4);
INSERT INTO comments_to_company VALUES (5, 1, 5);

INSERT INTO comments_to_deal VALUES (1, 2, 5);
INSERT INTO comments_to_deal VALUES (2, 2, 1);
INSERT INTO comments_to_deal VALUES (3, 5, 4);
INSERT INTO comments_to_deal VALUES (4, 1, 5);
INSERT INTO comments_to_deal VALUES (5, 4, 3);

INSERT INTO files_to_company VALUES (1, 2, 2);
INSERT INTO files_to_company VALUES (2, 5, 1);
INSERT INTO files_to_company VALUES (3, 1, 5);
INSERT INTO files_to_company VALUES (4, 2, 5);
INSERT INTO files_to_company VALUES (5, 1, 3);

INSERT INTO files_to_contact VALUES (1, 2, 3);
INSERT INTO files_to_contact VALUES (2, 3, 2);
INSERT INTO files_to_contact VALUES (3, 5, 4);
INSERT INTO files_to_contact VALUES (4, 4, 5);
INSERT INTO files_to_contact VALUES (5, 2, 5);

INSERT INTO files_to_deal VALUES (1, 1, 1);
INSERT INTO files_to_deal VALUES (2, 2, 2);
INSERT INTO files_to_deal VALUES (3, 3, 3);
INSERT INTO files_to_deal VALUES (4, 4, 4);
INSERT INTO files_to_deal VALUES (5, 5, 5);

INSERT INTO files_to_user VALUES (1, 4, 1);
INSERT INTO files_to_user VALUES (2, 5, 2);
INSERT INTO files_to_user VALUES (3, 1, 3);
INSERT INTO files_to_user VALUES (4, 5, 4);
INSERT INTO files_to_user VALUES (5, 3, 5);

INSERT INTO tags_to_company VALUES (1, 4, 2);
INSERT INTO tags_to_company VALUES (2, 5, 3);
INSERT INTO tags_to_company VALUES (3, 3, 1);
INSERT INTO tags_to_company VALUES (4, 4, 5);
INSERT INTO tags_to_company VALUES (5, 2, 4);

INSERT INTO tags_to_contact VALUES (1, 2, 3);
INSERT INTO tags_to_contact VALUES (2, 3, 4);
INSERT INTO tags_to_contact VALUES (3, 4, 5);
INSERT INTO tags_to_contact VALUES (4, 5, 1);
INSERT INTO tags_to_contact VALUES (5, 1, 2);

INSERT INTO tags_to_deal VALUES (1, 5, 4);
INSERT INTO tags_to_deal VALUES (2, 4, 3);
INSERT INTO tags_to_deal VALUES (3, 3, 2);
INSERT INTO tags_to_deal VALUES (4, 2, 1);
INSERT INTO tags_to_deal VALUES (5, 1, 5);

INSERT INTO comments_to_task VALUES (1, 5, 4);
INSERT INTO comments_to_task VALUES (2, 4, 3);
INSERT INTO comments_to_task VALUES (3, 3, 2);
INSERT INTO comments_to_task VALUES (4, 2, 1);
INSERT INTO comments_to_task VALUES (5, 1, 5);
