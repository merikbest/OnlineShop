package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;

import java.util.List;

public interface PerfumeService {
    Iterable<Perfume> findAll();

    List<Perfume> findByPerfumer(String perfumer);

    List<Perfume> findByPerfumeGender(String perfumeGender);

    void saveProductInfoById(String perfumeTitle, String perfumer, Integer year, String country, String perfumeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);

    Perfume save(Perfume perfume);
}
