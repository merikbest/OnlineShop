package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order save(Order order);
}
