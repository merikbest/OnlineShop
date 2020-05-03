package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Order;

public interface OrderService {
    Iterable<Order> findAll();

    Order save(Order order);
}
