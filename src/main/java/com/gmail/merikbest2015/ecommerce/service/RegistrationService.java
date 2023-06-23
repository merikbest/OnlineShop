package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.dto.UserRequest;

public interface RegistrationService {

    String registration(String captchaResponse, UserRequest user);

    boolean activateEmailCode(String code);
}
