package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.getOne(perfumeId);
    }

    @Override
    public List<Perfume> findPopularPerfumes() {
        List<Long> perfumeIds = Arrays.asList(11L, 39L, 56L, 119L, 59L, 47L, 95L, 89L, 98L, 52L, 40L, 92L, 99L);
        return perfumeRepository.findByIdIn(perfumeIds);
    }

    @Override
    public Page<Perfume> getPerfumes(Pageable pageable) {
        return perfumeRepository.findAll(pageable);
    }

    @Override
    public Page<Perfume> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable) {
        return perfumeRepository.findByPriceBetween(startingPrice, endingPrice, pageable);
    }

    @Override
    public Page<Perfume> getPerfumesByPerfumer(String perfumer, Pageable pageable) {
        return perfumeRepository.findByPerfumer(perfumer, pageable);
    }

    @Override
    public Page<Perfume> getPerfumesByGender(String perfumeGender, Pageable pageable) {
        return perfumeRepository.findByPerfumeGender(perfumeGender, pageable);
    }

    @Override
    public Page<Perfume> findByPerfumeGenderIn(List<String> perfumeGenders, Pageable pageable) {
        return perfumeRepository.findByPerfumeGenderIn(perfumeGenders, pageable);
    }

    @Override
    public Page<Perfume> searchPerfumes(String text, Pageable pageable) {
        return perfumeRepository.findByPerfumerOrPerfumeTitle(text, text, pageable);
    }

    @Override
    public Page<Perfume> findByPerfumerInAndPerfumeGenderIn(List<String> perfumers, List<String> genders, Pageable pageable) {
        return perfumeRepository.findByPerfumerInAndPerfumeGenderIn(perfumers, genders, pageable);
    }

    @Override
    public Page<Perfume> findByPerfumerInOrPerfumeGenderIn(List<String> perfumers, List<String> genders, Pageable pageable) {
        return perfumeRepository.findByPerfumerInOrPerfumeGenderIn(perfumers, genders, pageable);
    }

    @Override
    public Page<Perfume> findByPerfumerIn(List<String> perfumers, Pageable pageable) {
        return perfumeRepository.findByPerfumerIn(perfumers, pageable);
    }

    @Override
    public BigDecimal minPerfumePrice() {
        return perfumeRepository.minPerfumePrice();
    }

    @Override
    public BigDecimal maxPerfumePrice() {
        return perfumeRepository.maxPerfumePrice();
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
