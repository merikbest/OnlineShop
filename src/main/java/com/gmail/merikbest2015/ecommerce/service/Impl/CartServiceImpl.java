package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repository.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.repository.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> getPerfumesInCart(String email) {
        User user = userRepository.findByEmail(email);
        return user.getPerfumeList();
    }

    @Override
    @Transactional
    public void addPerfumeToCart(String username, Long perfumeId) {
        User user = userRepository.findByEmail(username);
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().add(perfume);
    }

    @Override
    @Transactional
    public void removePerfumeFromCart(String username, Long perfumeId) {
        User user = userRepository.findByEmail(username);
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().remove(perfume);
    }
}
