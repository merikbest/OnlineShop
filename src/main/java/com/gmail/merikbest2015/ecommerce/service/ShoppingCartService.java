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

    public void addToCart(User user, Long id, Integer amount) {
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setCartUser(user);
        } else if (cart.getCartItemList() != null || !cart.getCartItemList().isEmpty()) {
            for (CartItem cartItem : cart.getCartItemList()) {
                if (cartItem.getCartProduct().getId().equals(id)) {
                    cartItem.setAmount(cartItem.getAmount() + amount);

                    cartRepository.save(cart);
                }
            }
        }

        Optional optional = perfumeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Product not found.");
        }

        Perfume perfume = (Perfume) optional.get();

        CartItem cartItem = new CartItem();
        cartItem.setCart(user.getCart());
        cartItem.setCartProduct(perfume);

        cartItem.setCart(cart);
        //        if (cart.getCartItemList() == null) {
//            cart.setCartItemList(new ArrayList<>());
//        }
//
//        cart.getCartItemList().add(cartItem);
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(User user, Long id) {
        Optional<CartItem> optionalItem = cartItemRepository.findById(id);
        CartItem cartItem = optionalItem.get();
        cartItemRepository.delete(cartItem);
    }

    public User getOrder(User user) {
        Cart cart = user.getCart();
        int price = 0;

        for (CartItem cartItem : cart.getCartItemList()) {
            price = price + (cartItem.getCartProduct().getPrice() * cartItem.getAmount());
        }
        cart.setSubPrice(price);

        return user;
    }
}