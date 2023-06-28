package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.ChangePasswordRequest;
import com.gmail.merikbest2015.ecommerce.dto.request.EditUserRequest;
import com.gmail.merikbest2015.ecommerce.dto.response.MessageResponse;

public interface UserService {

    User getAuthenticatedUser();

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}
