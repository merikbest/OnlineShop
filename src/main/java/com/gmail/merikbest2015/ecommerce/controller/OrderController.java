package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.domain.dto.OrderRequest;
import com.gmail.merikbest2015.ecommerce.security.UserPrincipal;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String getOrder(@AuthenticationPrincipal UserPrincipal user, Model model) {
        model.addAttribute("perfumes", orderService.getOrder(user.getUsername()));
        return "order/order";
    }

    @GetMapping("/user/orders")
    public String getUserOrdersList(@AuthenticationPrincipal UserPrincipal user, Model model) {
        model.addAttribute("orders", orderService.getUserOrdersList(user.getUsername()));
        return "order/orders";
    }

    @GetMapping("/all/orders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/orders";
    }

    @PostMapping
    public String postOrder(@AuthenticationPrincipal UserPrincipal userPrincipal,
                            @Valid OrderRequest orderRequest,
                            BindingResult bindingResult, Model model) {
        User user = userService.findByEmail(userPrincipal.getUsername());

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("perfumes", user.getPerfumeList());
            return "order/order";
        }
        model.addAttribute("orderId", orderService.postOrder(user, orderRequest));
        return "order/finalize-order";
    }
}
