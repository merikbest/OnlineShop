package com.gmail.merikbest2015.ecommerce.repos;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
