package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.CART;

@Controller
@RequiredArgsConstructor
@RequestMapping(CART)
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getCart(@AuthenticationPrincipal User user, Model model) {
        List<Perfume> perfumes = cartService.getPerfumesInCart(user.getUsername());
        model.addAttribute("perfumes", perfumes);
        return "cart";
    }

    @PostMapping("/add")
    public String addPerfumeToCart(@AuthenticationPrincipal User user, @RequestParam("perfumeId") Long perfumeId) {
        cartService.addPerfumeToCart(user.getUsername(), perfumeId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removePerfumeFromCart(@AuthenticationPrincipal User user, @RequestParam("perfumeId") Long perfumeId) {
        cartService.removePerfumeFromCart(user.getUsername(), perfumeId);
        return "redirect:/cart";
    }
}