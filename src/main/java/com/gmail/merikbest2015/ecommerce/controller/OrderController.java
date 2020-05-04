package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String getOrder(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.findByUsername(userSession.getUsername());
        model.addAttribute("perfumes", userFromDB.getPerfumeList());

        return "order";
    }

    @PostMapping("/order")
    public String postOrder(
            @RequestParam Double totalPrice,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam String postIndex,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());

        Order order = new Order(user);
        order.getPerfumeList().addAll(user.getPerfumeList());
        order.setTotalPrice(totalPrice);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setCity(city);
        order.setAddress(address);
        order.setPostIndex(postIndex);
        order.setEmail(email);
        order.setPhoneNumber(phoneNumber);

        user.getPerfumeList().clear();

        orderService.save(order);

        return "redirect:/finalizeOrder";
    }

    @GetMapping("/finalizeOrder")
    public String finalizeOrder(Model model) {
        List<Order> orderList = orderService.findAll();
        Order orderIndex = orderList.get(orderList.size() - 1);

        model.addAttribute("orderIndex", orderIndex.getId());

        return "finalizeOrder";
    }

    @GetMapping("/orders")
    public String getOrderList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "orders";
    }
}
