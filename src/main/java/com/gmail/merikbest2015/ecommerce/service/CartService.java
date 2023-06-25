package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;

import java.util.List;

public interface CartService {

    List<Perfume> getPerfumesInCart();

    void addPerfumeToCart(Long perfumeId);

    void removePerfumeFromCart(Long perfumeId);
}
