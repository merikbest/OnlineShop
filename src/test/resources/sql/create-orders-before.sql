insert into orders (id, address, city, date, email, first_name, last_name, phone_number, post_index, total_price, user_id)
    values (111, 'Wall Street 1', 'New York', '2021-02-06', 'test123@test.com', 'John', 'Doe', '1234567890', 1234567890, 56, 122);

insert into orders_perfumes (order_id, perfumes_id) values (111, 2);
insert into orders_perfumes (order_id, perfumes_id) values (111, 4);
