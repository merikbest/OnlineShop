package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;

import java.util.List;

public interface CartService {

    List<Perfume> getPerfumesInCart(String username);

    void addPerfumeToCart(String username, Long perfumeId);

    void removePerfumeFromCart(String username, Long perfumeId);
}
