## Query Database

## Create View

### Create view to show list products customer bought: customer_id, customer_name, product_id, product_name, quantity, amount, created_date​

```sql
CREATE VIEW customer_product_purchases AS
SELECT
    c.id AS customer_id,
    c.name AS customer_name,
    p.id AS product_id,
    p.name AS product_name,
    id.quantity,
    id.amount,
    i.created_date
FROM
    customer c
JOIN
    invoice i ON c.id = i.customer_id
JOIN
    invoice_detail id ON i.id = id.invoice_id
JOIN
    product p ON id.product_id = p.id;
```

---

## Create Function

### Create a function calculating revenue by cashier: input cashier_id

```sql
DELIMITER //

CREATE FUNCTION calculate_revenue_by_cashier(input_cashier_id INT)
RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE total_revenue DECIMAL(10, 2);

    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE cashier_id = input_cashier_id;

    RETURN IFNULL(total_revenue, 0);
END //

DELIMITER ;

```

## Create Table

### Create table revenue_report: id, year, month, day, amount​

```sql
CREATE TABLE revenue_report (
    id INT PRIMARY KEY AUTO_INCREMENT,
    year INT NOT NULL,
    month INT NOT NULL,
    day INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

```

## Create Query

#### Create store procedures to calculate and store output in revenue_report table:​

- ### revenue of day: input day of year​

```sql
DELIMITER //

CREATE PROCEDURE calculate_daily_revenue(IN input_date DATE)
BEGIN
    DECLARE daily_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO daily_revenue
    FROM invoice
    WHERE created_date = input_date;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (YEAR(input_date), MONTH(input_date), DAY(input_date), daily_revenue);
END //

DELIMITER ;

```

- ### revenue of month: input month of year​

```sql
DELIMITER //

CREATE PROCEDURE calculate_monthly_revenue(IN input_year INT, IN input_month INT)
BEGIN
    DECLARE monthly_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO monthly_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year AND MONTH(created_date) = input_month;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, input_month, 0, monthly_revenue);
END //

DELIMITER ;

```

- ### revenue of year: input year​

```sql
DELIMITER //

CREATE PROCEDURE calculate_annual_revenue(IN input_year INT)
BEGIN
    DECLARE annual_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO annual_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, 0, 0, annual_revenue);
END //

DELIMITER ;

```

## USAGE EXAMPLE

### Calculate and Store Revenue for a Specific Day

```sql
CALL calculate_daily_revenue('2024-06-28');
```

### Calculate and Store Revenue for a Specific Month

```sql
CALL calculate_monthly_revenue(2024, 6);
```

### Calculate and Store Revenue for a Specific Year

```sql
CALL calculate_annual_revenue(2024);
```

## Restfull API best practice
Compare restful api best practice with Assignment 2 – lecture 5 based on [This article](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/).
### Explaination