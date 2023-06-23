package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.security.UserPrincipal;
import com.gmail.merikbest2015.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.CART;

@Controller
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getCart(@AuthenticationPrincipal UserPrincipal user, Model model) {
        model.addAttribute("perfumes", cartService.getPerfumesInCart(user.getUsername()));
        return "cart";
    }

    @PostMapping("/add")
    public String addPerfumeToCart(@AuthenticationPrincipal UserPrincipal user, @RequestParam("perfumeId") Long perfumeId) {
        cartService.addPerfumeToCart(user.getUsername(), perfumeId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removePerfumeFromCart(@AuthenticationPrincipal UserPrincipal user, @RequestParam("perfumeId") Long perfumeId) {
        cartService.removePerfumeFromCart(user.getUsername(), perfumeId);
        return "redirect:/cart";
    }
}
