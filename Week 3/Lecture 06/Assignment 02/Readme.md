# Initializa Database

## Create Table

```sql
-- Table to store customer information
CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15)
);

-- Table to store cashier information
CREATE TABLE cashier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Table to store product information
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Table to store invoice information
CREATE TABLE invoice (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    cashier_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    created_date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (cashier_id) REFERENCES cashier(id)
);

-- Table to store invoice detail information
CREATE TABLE invoice_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    product_id INT,
    product_price DECIMAL(10, 2) NOT NULL,
    invoice_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);

```

## Explaination Tables

- The customer table stores customer details.

- The cashier table stores cashier details.
- The product table stores product details.
- The invoice table stores general invoice information, including references to the customer and cashier involved.
- The invoice_detail table stores detailed information about each product included in the invoice.

---

## Defintion Rules

- invoice.amount is the sum of invoice_detail.amount for the respective invoice.
- invoice_detail.amount is calculated as quantity \* product_price.

```sql
-- Inserting sample data into the customer table
INSERT INTO customer (name, phone) VALUES ('John Doe', '123-456-7890');
INSERT INTO customer (name, phone) VALUES ('Jane Smith', '098-765-4321');

-- Inserting sample data into the cashier table
INSERT INTO cashier (name) VALUES ('Alice Johnson');
INSERT INTO cashier (name) VALUES ('Bob Brown');

-- Inserting sample data into the product table
INSERT INTO product (name, price) VALUES ('Product A', 10.00);
INSERT INTO product (name, price) VALUES ('Product B', 20.00);

-- Inserting sample data into the invoice table (initially without amount)
INSERT INTO invoice (customer_id, cashier_id, amount, created_date) VALUES (1, 1, 0, '2024-06-28');
INSERT INTO invoice (customer_id, cashier_id, amount, created_date) VALUES (2, 2, 0, '2024-06-28');

-- Inserting sample data into the invoice_detail table and calculating the amount
-- For invoice 1
INSERT INTO invoice_detail (quantity, product_id, product_price, invoice_id, amount) VALUES
(2, 1, 10.00, 1, 2 * 10.00),
(1, 2, 20.00, 1, 1 * 20.00);

-- For invoice 2
INSERT INTO invoice_detail (quantity, product_id, product_price, invoice_id, amount) VALUES
(3, 1, 10.00, 2, 3 * 10.00),
(2, 2, 20.00, 2, 2 * 20.00);

-- Updating the invoice table to set the correct amount
UPDATE invoice SET amount = (
    SELECT SUM(amount)
    FROM invoice_detail
    WHERE invoice_detail.invoice_id = invoice.id
);

```

## Explaination

- Insert sample data into the customer, cashier, and product tables.
- Insert sample data into the invoice table with an initial amount of 0.
- Insert sample data into the invoice_detail table, calculating the amount based on quantity and product_price.
- Update the invoice table to set the correct amount based on the sum of the invoice_detail amounts.
