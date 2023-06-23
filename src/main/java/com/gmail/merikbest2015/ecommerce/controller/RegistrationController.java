package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.dto.UserRequest;
import com.gmail.merikbest2015.ecommerce.service.RegistrationService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.REGISTRATION;

@Controller
@RequestMapping(REGISTRATION)
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ControllerUtils controllerUtils;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registration(@RequestParam("g-recaptcha-response") String captchaResponse,
                               @Valid UserRequest user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        String registrationResponse = registrationService.registration(captchaResponse, user);

        if (registrationResponse.equals("captchaError")) {
            model.addAttribute(registrationResponse, "Fill captcha");
            return "registration";
        }
        if (registrationResponse.equals("passwordError")) {
            model.addAttribute(registrationResponse, "Пароли не совпадают");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            return "registration";
        }
        if (registrationResponse.equals("usernameError")) {
            model.addAttribute(registrationResponse, "Пользователь существует!");
            return "registration";
        }
        redirectAttributes.addFlashAttribute(registrationResponse, "Письмо активации выслано на ваш email");
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateEmailCode(@PathVariable String code, Model model) {
        boolean isActivated = registrationService.activateEmailCode(code);
        model.addAttribute("messageType", isActivated ? "alert-success" : "alert-danger");
        model.addAttribute("message", isActivated ? "Пользователь успешно активирован" : "Код активации не найден");
        return "login";
    }
}
