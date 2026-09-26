-- 1) N+1 prevention example: fetch orders with items in one query
SELECT o.id, o.customer_id, o.total_amount, o.order_status,
       oi.product_id, oi.quantity, oi.unit_price
FROM orders o
LEFT JOIN order_items oi ON oi.order_id = o.id
WHERE o.customer_id = 1
ORDER BY o.id;

-- 2) Top customers by total spend
SELECT c.id, c.name, SUM(o.total_amount) AS total_spend
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
GROUP BY c.id, c.name
ORDER BY total_spend DESC;

-- 3) Inventory check before placing order
SELECT p.id, p.name, p.stock_quantity
FROM products p
WHERE p.id = 1 FOR UPDATE;

-- 4) Payment status and order lifecycle
SELECT o.id, c.name, o.order_status, o.total_amount
FROM orders o
JOIN customers c ON c.id = o.customer_id
WHERE o.order_status IN ('PENDING', 'PAID');

-- 5) Duplicate order protection through unique constraint
-- A query can ensure a user cannot create two identical pending orders:
SELECT customer_id, product_id, COUNT(*)
FROM order_items oi
JOIN orders o ON o.id = oi.order_id
WHERE o.order_status = 'PENDING'
GROUP BY customer_id, product_id
HAVING COUNT(*) > 1;

-- 6) Common transaction pattern
BEGIN;
UPDATE products
SET stock_quantity = stock_quantity - 1
WHERE id = 3 AND stock_quantity > 0;

INSERT INTO orders (customer_id, order_status, total_amount)
VALUES (2, 'PENDING', 60.00);

COMMIT;

-- 7) Index usage example with range
SELECT * FROM orders
WHERE created_at BETWEEN NOW() - INTERVAL '30 days' AND NOW();
