package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Cart;
import com.gmail.merikbest2015.ecommerce.domain.CartItem;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.CartItemRepository;
import com.gmail.merikbest2015.ecommerce.repos.CartRepository;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(User user, Long id) {
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setCartUser(user);
        }

        cartRepository.save(cart);

        Optional optional = perfumeRepository.findById(id);
        Perfume perfume = (Perfume) optional.get();

        CartItem cartItem = new CartItem();
        cartItem.setCart(user.getCart());
        cartItem.setCartProduct(perfume);

        cartItemRepository.save(cartItem);
    }
}