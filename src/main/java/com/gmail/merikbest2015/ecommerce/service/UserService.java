package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.ChangePasswordRequest;
import com.gmail.merikbest2015.ecommerce.dto.response.MessageResponse;

import java.util.List;

public interface UserService {

    User getAuthenticatedUser();

    User getUserById(Long userId);

    List<User> getUsers();

    MessageResponse changePassword(ChangePasswordRequest request);
}
