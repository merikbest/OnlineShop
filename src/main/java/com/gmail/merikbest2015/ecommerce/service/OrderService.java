package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.OrderRequest;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Perfume> getOrder(String email);

    Long postOrder(User user, OrderRequest orderRequest);

    List<Order> getUserOrdersList(String email);
}
