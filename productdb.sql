DROP DATABASE productdb;
CREATE DATABASE IF NOT EXISTS productdb;
USE productdb;

CREATE TABLE IF NOT EXISTS Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT 
);

CREATE TABLE IF NOT EXISTS Category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    product_year INT,
    image VARCHAR(500),
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Categories(id)
);

INSERT INTO Categories (name) VALUES
('Electronics'),
('Clothing'),
('Books'),
('Furniture'),
('Toys');

INSERT INTO Products (name, price, product_year, image, category_id) VALUES
('Smartphone', 699.99, 2023, 'https://img.freepik.com/free-vector/realistic-display-smartphone-with-different-apps_52683-30241.jpg', 1), -- Electronics
('Laptop', 1199.99, 2022, 'https://m.media-amazon.com/images/I/61Qe0euJJZL.jpg', 1), -- Electronics
('T-Shirt', 19.99, 2023, 'https://dictionary.cambridge.org/images/thumb/Tshirt_noun_001_18267.jpg?version=6.0.43', 2), -- Clothing
('Novel', 9.99, 2021, 'https://cdn0.fahasa.com/media/catalog/product/m/a/mat-biec_bia-mem_in-lan-thu-44.jpg', 3), -- Books
('Desk', 199.99, 2020, 'https://www.ikea.com/us/en/images/products/micke-desk-black-brown__0735981_pe740299_s5.jpg?f=s', 4), -- Furniture
('Sofa', 899.99, 2022, 'https://cozyliving.com.vn/cdn/shop/products/Cozy-Day23144copy_f27f18ca-fdc9-4686-bf68-a141ecc754af.jpg?v=1655780871', 4); -- Furniture