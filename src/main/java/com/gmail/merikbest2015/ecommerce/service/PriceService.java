package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Cart;
import com.gmail.merikbest2015.ecommerce.domain.CartItem;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class PriceService {

    public Cart calculatePrice(Cart cart) {
        Float totalPrice = 0F;

        for (CartItem item : cart.getCartItemList()) {
            totalPrice = totalPrice + (((item.getCartProduct().getPrice() * item.getCartProduct().getAmount())));
        }

//        cart.setTotalPrice(roundTwoDecimals(totalPrice));

        return cart;
    }

    private float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }
}