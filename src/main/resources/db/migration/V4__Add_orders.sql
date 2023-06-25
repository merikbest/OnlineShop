INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    VALUES (1, 'Wall Street1', 'New York', '2021-04-07', 'test123@test.com', 'John', 'Doe', '1234567890', 1234567890, 840, 2);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    VALUES (2, 'Wall Street1', 'New York', '2021-04-07', 'test123@test.com', 'John', 'Doe', '1234567890', 1234567890, 240, 2);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    VALUES (3, 'Tverskaya street 1', 'Moscow', '2021-04-07', 'ivan123@test.com', 'Ivan', 'Ivanov', '1234567890', 1234567890, 163, 3);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    VALUES (4, 'Tverskaya street 1', 'Moscow', '2021-04-07', 'ivan123@test.com', 'Ivan', 'Ivanov', '1234567890', 1234567890, 780, 3);
INSERT INTO orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    VALUES (5, 'Tverskaya street 1', 'Moscow', '2021-04-07', 'ivan123@test.com', 'Ivan', 'Ivanov', '1234567890', 1234567890, 196, 3);

INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (1, 33);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (1, 34);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (2, 39);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (2, 43);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (3, 77);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (3, 85);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (3, 108);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (4, 16);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (4, 17);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (5, 86);
INSERT INTO orders_perfumes (order_id, perfumes_id) VALUES (5, 91);
