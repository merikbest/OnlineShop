package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Cart;
import com.gmail.merikbest2015.ecommerce.domain.CartItem;
import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.CartItemRepository;
import com.gmail.merikbest2015.ecommerce.repos.CartRepository;
import com.gmail.merikbest2015.ecommerce.repos.OrderRepository;
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

import java.util.Calendar;
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

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("cart")
    public String addToCart(@AuthenticationPrincipal User user, Long id, @RequestParam Integer amount) {
        user = userRepository.findByUsername(user.getUsername());
        shoppingCartService.addToCart(user, id, amount);

        return "main";
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

        return "main";
    }

    @GetMapping("order")
    public String getOrder(@AuthenticationPrincipal User user, Model model) {
        User userOrder = shoppingCartService.getOrder(user);
        model.addAttribute("user", userOrder);

        return "order";
    }

    @PostMapping("order")
    public String createOrder(@AuthenticationPrincipal User user,
                              @RequestParam String address,
                              @RequestParam String name) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setName(name);
        order.setDate(Calendar.getInstance().getTime());
        order.setTotalPrice(user.getCart().getSubPrice());
        orderRepository.save(order);

        Cart cart = new Cart();
        cartRepository.save(cart);

        return "redirect:/confirmed";
    }

    @GetMapping("confirmed")
    public String confirmedOrder(@AuthenticationPrincipal User user, Model model) {
        Order order = user.getOrderList().get(user.getOrderList().size() - 1);
        model.addAttribute("order", order);

        return "confirmed";
    }
}