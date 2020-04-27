package com.gmail.merikbest2015.ecommerce.repos;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
