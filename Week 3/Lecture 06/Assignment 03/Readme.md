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
