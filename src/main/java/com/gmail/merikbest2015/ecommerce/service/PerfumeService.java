package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface PerfumeService {
    Iterable<Perfume> findAll();

    Page<Perfume> findAll(Pageable pageable);

    Page<Perfume> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

    Page<Perfume> findByPerfumer(String perfumer, Pageable pageable);

    Page<Perfume> findByPerfumeGender(String perfumeGender, Pageable pageable);

    Page<Perfume> findByPerfumerOrPerfumeTitle(String perfumer, String perfumeTitle, Pageable pageable);

    Page<Perfume> findByPerfumerIn (List<String> perfumers, Pageable pageable);

    BigDecimal minPerfumePrice();

    BigDecimal maxPerfumePrice();

    void saveProductInfoById(String perfumeTitle, String perfumer, Integer year, String country, String perfumeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);

    Perfume save(Perfume perfume);
}
