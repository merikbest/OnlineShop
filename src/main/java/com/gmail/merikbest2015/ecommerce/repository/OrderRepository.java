package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "perfumeList")
    List<Order> findAll();

    List<Order> findOrderByUserId(Long userId);

    List<Order> findOrderByUser(User user);
}