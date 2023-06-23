package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.security.UserPrincipal;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.USER;

@Controller
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PerfumeService perfumeService;

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "user/user-cabinet";
    }

    @GetMapping("/edit")
    public String getProfileInfo(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userService.findByEmail(userPrincipal.getUsername());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "user/user-edit-profile";
    }

    // TODO refactor: remove email edit
    @PostMapping("/edit")
    public String updateProfileInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                    @RequestParam String password,
                                    @RequestParam String email) {
        userService.updateProfile(userPrincipal, password, email);
        return "redirect:/user/user-cabinet";
    }
}
