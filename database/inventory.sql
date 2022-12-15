DROP TABLE IF EXISTS items, transactions;

CREATE TABLE items (
	location_code varchar(2) NOT NULL,
	item_name varchar (20) NOT NULL,
	item_cost decimal (3, 2) NOT NUll,
	item_type varchar (10) NOT NULL,
	item_stock int NOT NULL
);

CREATE SEQUENCE seq_transactions
	INCREMENT BY 1
	START WITH 1
	NO MAXVALUE;

CREATE TABLE transactions (
	transaction_id varchar (1) NOT NULL DEFAULT nextval('seq_transactions'),
	transaction_date_time varchar (50) NOT NULL,
	action_taken varchar (20) NOT NULL,
	transaction_amount decimal (5, 2) NOT NULL,
	balance decimal (5, 2) NOT NULL	
);

INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('A1', 'Potato Crisps', 3.05, 'Chips', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('A2', 'Stackers', 1.45, 'Chips', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('A3', 'Grain Waves', 2.75, 'Chips', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('A4', 'Cloud Popcorn', 3.75, 'Chips', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('B1', 'Moon Pie', 1.80, 'Candy', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('B2', 'Cowtales', 1.50, 'Candy', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('B3', 'Wonka Bar', 1.50, 'Candy', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('B4', 'Crunchie', 1.75, 'Candy', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('C1', 'Cola', 1.25, 'Drink', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('C2', 'Dr. Salt', 1.50, 'Drink', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('C3', 'Mountain Melter', 1.50, 'Drink', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('C4', 'Heavy', 1.50, 'Drink', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('D1', 'U-Chews', 0.85, 'Gum', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('D2', 'Little League Chew', 0.95, 'Gum', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('D3', 'Chiclets', 0.75, 'Gum', 5);
INSERT INTO items (location_code, item_name, item_cost, item_type, item_stock) VALUES ('D4', 'Triplemint', 0.75, 'Gum', 5);

COMMIT;