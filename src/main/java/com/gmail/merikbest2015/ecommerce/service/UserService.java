package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    User getAuthenticatedUser();

    List<User> getUsers();

    void changePassword(String password);
}
