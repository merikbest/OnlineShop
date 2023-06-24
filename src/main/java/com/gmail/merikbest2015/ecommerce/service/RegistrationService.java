package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.dto.RegistrationResponse;
import com.gmail.merikbest2015.ecommerce.dto.UserRequest;

public interface RegistrationService {

    RegistrationResponse registration(String captchaResponse, UserRequest user);

    boolean activateEmailCode(String code);
}
