CREATE TABLE IF NOT EXISTS tbl_user(
	user_id INT AUTO_INCREMENT,
	user_nm VARCHAR(100) NOT NULL,
	user_email VARCHAR(75) NOT NULL,
	PRIMARY KEY (user_id),
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_user_email ON tbl_user(user_email);
CREATE UNIQUE INDEX IF NOT EXISTS idx_user_nm ON tbl_user(user_nm);

CREATE TABLE IF NOT EXISTS tbl_product(
	product_id INT AUTO_INCREMENT,
	product_nm VARCHAR(100) NOT NULL,
	product_url VARCHAR(255) NOT NULL,
	product_vl DECIMAL(10,2) NOT NULL,
	PRIMARY KEY (product_id),
);

CREATE TABLE IF NOT EXISTS tbl_cart(
	cart_id INT AUTO_INCREMENT,
	user_id INT NOT NULL,
	PRIMARY KEY (cart_id),
);

CREATE TABLE IF NOT EXISTS tbl_item(
	cart_id INT NOT NULL,
	product_id INT NOT NULL,
	product_item_vl DECIMAL(10,2) NOT NULL,
	product_item_qty INT NOT NULL,
	product_item_nm VARCHAR(100) NOT NULL,
	product_item_url VARCHAR(255) NOT NULL,
	PRIMARY KEY (cart_id, product_id),
);

ALTER TABLE tbl_item ADD FOREIGN KEY ( product_id ) REFERENCES tbl_product( product_id );




