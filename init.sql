DROP TABLE PRODUCTS IF EXISTS;
DROP TABLE ORDERS IF EXISTS;

CREATE TABLE PRODUCTS (
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(30),
PRICE INT,
COMMENTS VARCHAR(30)
);

CREATE TABLE ORDERS (
ID INT PRIMARY KEY AUTO_INCREMENT,
NUMBER VARCHAR(30),
TYPE TINYINT,
CONTACT_ID INT,
FOREIGN KEY (CONTACT_ID) REFERENCES CONTACTS(ID) ON DELETE CASCADE
);

