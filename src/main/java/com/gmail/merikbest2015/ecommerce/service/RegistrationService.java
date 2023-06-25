package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.dto.response.RegistrationResponse;
import com.gmail.merikbest2015.ecommerce.dto.request.UserRequest;

public interface RegistrationService {

    RegistrationResponse registration(String captchaResponse, UserRequest user);

    boolean activateEmailCode(String code);
}
