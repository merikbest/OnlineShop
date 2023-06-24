package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import com.gmail.merikbest2015.ecommerce.domain.Role;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.CaptchaResponse;
import com.gmail.merikbest2015.ecommerce.dto.RegistrationResponse;
import com.gmail.merikbest2015.ecommerce.dto.UserRequest;
import com.gmail.merikbest2015.ecommerce.repository.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Value("${recaptcha.url}")
    private String captchaUrl;

    @Value("${recaptcha.secret}")
    private String secret;

    @Override
    @Transactional
    public RegistrationResponse registration(String captchaResponse, UserRequest userRequest) {
        String url = String.format(captchaUrl, secret, captchaResponse);
        CaptchaResponse response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponse.class);

        if (!response.isSuccess()) {
            return new RegistrationResponse("captchaError", ErrorMessage.CAPTCHA_ERROR);
        }
        if (userRequest.getPassword() != null && !userRequest.getPassword().equals(userRequest.getPassword2())) {
            return new RegistrationResponse("passwordError", ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            return new RegistrationResponse("usernameError", ErrorMessage.EMAIL_IN_USE);
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        mailSender.sendEmail(user);
        return new RegistrationResponse("success", "Activation email has been sent to your email");
    }

    @Override
    public boolean activateEmailCode(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
}
