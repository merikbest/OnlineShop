package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    Order getUserOrder(Long orderId);

    List<Perfume> getOrdering();

    List<Order> getUserOrdersList();

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getAllOrders();

    Long postOrder(User user, OrderRequest orderRequest);
}
