INSERT INTO category_entity (name, parent_id)
VALUES ('Electronics', NULL);
INSERT INTO category_entity (name, parent_id)
VALUES ('Home Appliances', NULL);
INSERT INTO category_entity (name, parent_id)
VALUES ('Fashion', NULL);

INSERT INTO category_entity (name, parent_id)
VALUES ('Mobile Phones', 1);
INSERT INTO category_entity (name, parent_id)
VALUES ('Laptops', 1);
INSERT INTO category_entity (name, parent_id)
VALUES ('Accessories', 1);

INSERT INTO category_entity (name, parent_id)
VALUES ('Kitchen', 2);
INSERT INTO category_entity (name, parent_id)
VALUES ('Laundry', 2);

INSERT INTO category_entity (name, parent_id)
VALUES ('Men', 3);
INSERT INTO category_entity (name, parent_id)
VALUES ('Women', 3);

INSERT INTO product_entity (name, description, price, category_id, available)
VALUES ('iPhone 13', 'Apple smartphone with 128GB storage', 799.99, 4, TRUE),
       ('Samsung Galaxy S22', 'Samsung flagship phone with 128GB storage', 699.99, 4, TRUE),
       ('OnePlus 10 Pro', 'OnePlus high-end phone with 256GB storage', 599.99, 4, TRUE);

INSERT INTO product_entity (name, description, price, category_id, available)
VALUES ('MacBook Pro 14"', 'Apple laptop with M1 Pro chip', 1999.99, 5, TRUE),
       ('Dell XPS 13', 'High-performance ultrabook with 16GB RAM', 1499.99, 5, TRUE),
       ('Lenovo ThinkPad X1 Carbon', 'Business laptop with 512GB SSD', 1299.99, 5, TRUE);

INSERT INTO product_entity (name, description, price, category_id, available)
VALUES ('Instant Pot Duo', 'Multi-use programmable pressure cooker', 99.99, 7, TRUE),
       ('Air Fryer XL', 'Large capacity air fryer with digital display', 129.99, 7, TRUE);

INSERT INTO product_entity (name, description, price, category_id, available)
VALUES ('Mens Leather Jacket', 'Black leather jacket, slim fit', 199.99, 9, TRUE),
       ('Casual Sneakers', 'Comfortable sneakers for daily use', 69.99, 9, TRUE);

INSERT INTO product_entity (name, description, price, category_id, available)
VALUES ('Womens Handbag', 'Stylish handbag with multiple compartments', 79.99, 10, TRUE),
       ('Ankle Boots', 'Elegant ankle boots for formal wear', 129.99, 10, TRUE);
