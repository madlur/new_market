create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into products (title, price)
values
('Bread', 24),
('Milk', 65),
('Cheese', 320),
('Cheese2', 322),
('Cheese3', 323),
('Cheese4', 324),
('Cheese5', 325),
('Cheese6', 326),
('Cheese7', 327),
('Cheese8', 328),
('Cheese9', 328),
('Cheese10', 328),
('Cheese11', 328),
('Cheese12', 328),
('Cheese13', 328),
('Cheese14', 328),
('Cheese15', 328);

create table order_items (
    id                      bigserial primary key,
    title                   varchar(255),
    quantity                int,
    price_per_item          int,
    price                   int
);


-- CREATE TABLE customer (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     customer_name VARCHAR(100)
-- );
--
-- CREATE TABLE product (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     title VARCHAR(100),
--     cost INT,
--     created_at timestamp default current_timestamp,
--     updated_at timestamp default current_timestamp
-- );
--
-- CREATE TABLE orders (
--     order_id INT AUTO_INCREMENT PRIMARY KEY,
--     customer_id INT,
--     product_id INT,
--     price INT
-- --    ,
-- --    FOREIGN KEY (customer_id) REFERENCES customer(id),
-- --    FOREIGN KEY (product_id) REFERENCES product(id)
-- );
--
--
--
-- INSERT INTO `customer` (`id`, `customer_name`) VALUES
-- (1, 'Adam'),
-- (2, 'Andy'),
-- (3, 'Joe'),
-- (4, 'Sandy');
--
-- INSERT INTO `product` (`id`, `title`, `cost`) VALUES
-- (1, 'Молоко', 59),
-- (2, 'Гречка', 30),
-- (3, 'Творог', 14),
-- (4, 'Творог1', 14),
-- (5, 'Творог2', 14),
-- (6, 'Творог3', 14),
-- (7, 'Творог4', 14),
-- (8, 'Творог5', 14),
-- (9, 'Творог6', 14),
-- (10, 'Творог7', 14),
-- (11, 'Творог8', 14),
-- (12, 'Творог9', 14),
-- (13, 'Творог10', 14),
-- (14, 'Творог11', 14),
-- (15, 'Творог12', 14),
-- (16, 'Творог13', 14),
-- (17, 'Творог14', 14),
-- (18, 'Творог15', 14),
-- (19, 'Творог16', 14),
-- (20, 'Кетчуп17', 75),
-- (21, 'Кетчуп18', 9999999);
--
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('1', '1', '59');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('1', '4', '30');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('2', '2', '14');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('1', '3', '75');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('3', '4', '30');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('3', '1', '59');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('2', '3', '75');
-- -- INSERT INTO `orders` (`customer_id`, `product_id`, `price`) VALUES ('1', '1', '59');
