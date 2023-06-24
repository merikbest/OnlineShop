package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByActivationCode(String code);
}