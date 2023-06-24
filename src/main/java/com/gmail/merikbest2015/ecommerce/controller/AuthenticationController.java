package com.gmail.merikbest2015.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.AUTH;

@Controller
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthenticationController {

    @GetMapping("/reset")
    public String passwordReset() {
        return "user/password-reset";
    }

    // TODO add password reset endpoint (post /reset)

    // TODO add password forgot endpoint

}
