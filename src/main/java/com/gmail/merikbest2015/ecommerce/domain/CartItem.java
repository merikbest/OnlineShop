package com.gmail.merikbest2015.ecommerce.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {
    private static final long serialVersionUID = -6428510751155205244L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Perfume cartProduct;

    @Column(name = "amount")
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Perfume getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(Perfume cartProduct) {
        this.cartProduct = cartProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
