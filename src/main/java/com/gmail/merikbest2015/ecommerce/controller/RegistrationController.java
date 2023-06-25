package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.dto.response.RegistrationResponse;
import com.gmail.merikbest2015.ecommerce.dto.request.UserRequest;
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
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("user", user);
            return "registration";
        }
        RegistrationResponse registrationResponse = registrationService.registration(captchaResponse, user);

        if (!registrationResponse.getResponse().equals("success")) {
            model.addAttribute(registrationResponse.getResponse(), registrationResponse.getMessage());
            model.addAttribute("user", user);
            return "registration";
        }
        redirectAttributes.addFlashAttribute(registrationResponse.getResponse(), registrationResponse.getMessage());
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateEmailCode(@PathVariable String code, Model model) {
        boolean isActivated = registrationService.activateEmailCode(code);
        model.addAttribute("messageType", isActivated ? "alert-success" : "alert-danger");
        model.addAttribute("message", isActivated ? "User successfully activated." : "Activation code not found");
        return "login";
    }
}
