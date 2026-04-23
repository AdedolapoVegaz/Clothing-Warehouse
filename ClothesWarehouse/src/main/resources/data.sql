---USERS
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$PG84w8T5NGO2vERbYpojZul5Rbh89YjEjMUJTPvdWXVsngAKCG4Bu', 'ROLE_ADMIN');

INSERT INTO users (username, password, role)
VALUES ('employee', '$2a$10$PG84w8T5NGO2vERbYpojZul5Rbh89YjEjMUJTPvdWXVsngAKCG4Bu', 'ROLE_EMPLOYEE');

-- ITEMS
INSERT INTO item (name, brand, year_of_creation, price)
VALUES ('Hoodie', 'NIKE', 2025, 79.99);

INSERT INTO item (name, brand, year_of_creation, price)
VALUES ('Jacket', 'ADIDAS', 2024, 129.99);

INSERT INTO item (name, brand, year_of_creation, price)
VALUES ('Sneakers', 'NIKE', 2025, 120.00);