package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import com.gmail.merikbest2015.ecommerce.dto.request.ChangePasswordRequest;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.USER;

@Controller
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PerfumeService perfumeService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/account")
    public String userAccount(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return "user/user-account";
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return "user/user-info";
    }

    @PostMapping("/change/password")
    public String changePassword(@Valid ChangePasswordRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            return "user/password-reset";
        }
        if (!request.getPassword().equals(request.getPassword2())) {
            model.addAttribute("passwordError", ErrorMessage.PASSWORDS_DO_NOT_MATCH);
            return "user/password-reset";
        }
        userService.changePassword(request.getPassword());
        model.addAttribute("success", "Password successfully changed!");
        return "user/password-reset";
    }
}
