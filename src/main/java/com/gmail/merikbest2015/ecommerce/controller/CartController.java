package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.CartItem;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.CartItemRepository;
import com.gmail.merikbest2015.ecommerce.repos.CartRepository;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("cart")
    public String addToCart(@AuthenticationPrincipal User user, Long id, @RequestParam Integer amount) {
        user = userRepository.findByUsername(user.getUsername());
        shoppingCartService.addToCart(user, id, amount);

        return "redirect:/main";
    }

    @GetMapping("cart")
    public String getCart(@AuthenticationPrincipal User user, Model model) {
        user = userRepository.findByUsername(user.getUsername());
        model.addAttribute("cart", user.getCart());

        return "cart";
    }

    @GetMapping("/delete/{id}")
    public String removeFromCart(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        shoppingCartService.removeFromCart(user, id);

        return "redirect:/main";
    }
}