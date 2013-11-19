BEGIN TRANSACTION;

CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO android_metadata VALUES('en_US');

CREATE TABLE ingredient (_id INTEGER PRIMARY KEY, name TEXT);

CREATE TABLE p_i (p_id INTEGER, i_id INTEGER);

CREATE TABLE pizza (_id INTEGER PRIMARY KEY, name TEXT, price NUMERIC, rating NUMERIC, restaurant_id INTEGER);
INSERT INTO pizza VALUES(1,'Vesuvio',55.55,3,1);
INSERT INTO pizza VALUES(2,'Hawaii',62.22,4,1);
INSERT INTO pizza VALUES(3,'Kebab',78.55,5,1);

CREATE TABLE restaurant (_id INTEGER PRIMARY KEY, name TEXT, address TEXT, phone NUMERIC, rating NUMERIC);
INSERT INTO restaurant VALUES(1,'Adonis','Stockholm',04727362784,4);

COMMIT;
