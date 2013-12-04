BEGIN TRANSACTION;

CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO android_metadata VALUES('en_US');

CREATE TABLE ingredient (_id INTEGER PRIMARY KEY, name TEXT);

CREATE TABLE p_i (p_id INTEGER, i_id INTEGER);

CREATE TABLE pizza (_id INTEGER PRIMARY KEY, name TEXT, price NUMERIC, rating INTEGER, r_id INTEGER);

CREATE TABLE restaurant (_id INTEGER PRIMARY KEY, name TEXT, address TEXT, phone TEXT, rating INTEGER);

COMMIT;