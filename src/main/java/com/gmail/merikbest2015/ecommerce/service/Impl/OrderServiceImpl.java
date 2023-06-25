package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.OrderRequest;
import com.gmail.merikbest2015.ecommerce.repository.OrderRepository;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order getOrder(Long orderId) {
        // TODO add query
        return orderRepository.getById(orderId);
    }

    @Override
    public List<Perfume> getOrdering() {
        User user = userService.getAuthenticatedUser();
        return user.getPerfumeList();
    }

    @Override
    public List<Order> getUserOrdersList() {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId());
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Long postOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getPerfumes().addAll(user.getPerfumeList());
        orderRepository.save(order);
        user.getPerfumeList().clear();
        return order.getId();
    }
}
