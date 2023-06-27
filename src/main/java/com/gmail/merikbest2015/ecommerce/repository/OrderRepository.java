package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"perfumes", "user", "user.roles"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"perfumes", "user", "user.roles"})
    Optional<Order> getById(Long orderId);

    @EntityGraph(attributePaths = {"perfumes"})
    Optional<Order> getByIdAndUserId(Long orderId, Long userId);

    List<Order> findOrderByUserId(Long userId);
}
