package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.OrderRequest;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.ORDER;

@Controller
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return "order";
    }

    @GetMapping
    public String getOrdering(Model model) {
        model.addAttribute("perfumes", orderService.getOrdering());
        return "ordering";
    }

    @GetMapping("/user/orders")
    public String getUserOrdersList(Model model) {
        model.addAttribute("orders", orderService.getUserOrdersList());
        return "orders";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all/orders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest, BindingResult bindingResult, Model model) {
        User user = userService.getAuthenticatedUser();

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("perfumes", user.getPerfumeList());
            return "ordering";
        }
        model.addAttribute("orderId", orderService.postOrder(user, orderRequest));
        return "order-finalize";
    }
}
