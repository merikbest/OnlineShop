package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.OrderRepository;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Perfume> getOrder(String username) {
        User user = userRepository.findByUsername(username);
        return user.getPerfumeList();
    }

    @Override
    public Long finalizeOrder() {
        List<Order> orderList = orderRepository.findAll();
        Order order = orderList.get(orderList.size() - 1);
        return order.getId();
    }

    @Override
    public List<Order> getUserOrdersList(String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findOrderByUserId(user.getId());
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }
}
