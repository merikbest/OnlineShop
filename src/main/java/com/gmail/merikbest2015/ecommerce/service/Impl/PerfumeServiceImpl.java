package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeServiceImpl implements PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public Iterable<Perfume> findAll() {
        return perfumeRepository.findAll();
    }

    @Override
    public List<Perfume> findByPerfumer(String perfumer) {
        return perfumeRepository.findByPerfumer(perfumer);
    }

    @Override
    public List<Perfume> findByPerfumeGender(String perfumeGender) {
        return perfumeRepository.findByPerfumeGender(perfumeGender);
    }

    @Override
    public void saveProductInfoById(String perfumeTitle, String perfumer, Integer year, String country,
                                    String perfumeGender, String fragranceTopNotes, String fragranceMiddleNotes,
                                    String fragranceBaseNotes, String description, String filename,
                                    Integer price, String volume, String type, Long id
    ) {
        perfumeRepository.saveProductInfoById(perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes,
                fragranceMiddleNotes, fragranceBaseNotes, description, filename, price, volume, type, id);
    }

    @Override
    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }
}
