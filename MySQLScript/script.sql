CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    added_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    added_by VARCHAR(50) NOT NULL
);

CREATE TABLE product_price (
    price_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    price DECIMAL(10, 2) NOT NULL,
    discount_percent INT DEFAULT 0,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE product_price_change_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    old_price DECIMAL(10, 2) NOT NULL,
    new_price DECIMAL(10, 2) NOT NULL,
    old_discount_percent INT NOT NULL,
    new_discount_percent INT NOT NULL,
    operation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    performed_by VARCHAR(50) NOT NULL,
    operation_type ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

SELECT
    p.name AS product_name,
    p.category AS product_category,
    pp.price AS product_price,
    pp.updated_by AS updated_by,
    pp.updated_time AS updated_time
FROM
    product p
JOIN
    product_price pp ON p.product_id = pp.product_id;
