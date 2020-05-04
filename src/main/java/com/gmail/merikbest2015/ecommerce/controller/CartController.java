package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.findByUsername(userSession.getUsername());
        model.addAttribute("perfumes", userFromDB.getPerfumeList());

        return "cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(
            @RequestParam("buttonAddToCart") Perfume perfume,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());
        user.getPerfumeList().add(perfume);
        userService.save(user);

        return "redirect:/cart";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(
            @RequestParam(value = "perfumeId") Perfume perfume,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());

        if (perfume != null) {
            user.getPerfumeList().remove(perfume);
        }

        userService.save(user);

        return "redirect:/cart";
    }
}