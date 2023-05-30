package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String getOrder(@AuthenticationPrincipal User user, Model model) {
        List<Perfume> perfumes = orderService.getOrder(user.getUsername());
        model.addAttribute("perfumes", perfumes);
        return "order/order";
    }

    @PostMapping("/order")
    public String postOrder(@AuthenticationPrincipal User userSession, @Valid Order validOrder,
                            BindingResult bindingResult, Model model) {
        User user = userService.findByUsername(userSession.getUsername());
        Order order = new Order(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("perfumes", user.getPerfumeList());

            return "order/order";
        } else {
            order.getPerfumeList().addAll(user.getPerfumeList());
            order.setTotalPrice(validOrder.getTotalPrice());
            order.setFirstName(validOrder.getFirstName());
            order.setLastName(validOrder.getLastName());
            order.setCity(validOrder.getCity());
            order.setAddress(validOrder.getAddress());
            order.setPostIndex(validOrder.getPostIndex());
            order.setEmail(validOrder.getEmail());
            order.setPhoneNumber(validOrder.getPhoneNumber());

            user.getPerfumeList().clear();

            orderService.save(order);
        }
        return "redirect:/finalizeOrder";
    }

    @GetMapping("/finalizeOrder")
    public String finalizeOrder(Model model) {
        Long orderIndex = orderService.finalizeOrder();
        model.addAttribute("orderIndex", orderIndex);
        return "order/finalizeOrder";
    }

    @GetMapping("/userOrders")
    public String getUserOrdersList(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getUserOrdersList(user.getUsername());
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/orders")
    public String getAllOrdersList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/orders";
    }
}
