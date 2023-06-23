package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.security.UserPrincipal;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(Long userId);

    User findByEmail(String email);

    List<User> findAll();

    void userSave(String username, Map<String, String> form, User user);

    void updateProfile(UserPrincipal userPrincipal, String password, String email);
}
