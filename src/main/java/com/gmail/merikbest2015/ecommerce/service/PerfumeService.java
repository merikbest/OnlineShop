package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.PerfumeRequest;
import com.gmail.merikbest2015.ecommerce.dto.PerfumeSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface PerfumeService {

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> findPopularPerfumes();

    Page<Perfume> getPerfumes(Pageable pageable);

    Page<Perfume> getPerfumesByPerfumer(String perfumer, Pageable pageable);

    Page<Perfume> getPerfumesByGender(String perfumeGender, Pageable pageable);

    Page<Perfume> searchPerfumes(String text, Pageable pageable);

    BigDecimal minPerfumePrice();

    BigDecimal maxPerfumePrice();

    Page<Perfume> getPerfumesByFilterParams(PerfumeSearchRequest searchRequest, Pageable pageable);

    void savePerfume(PerfumeRequest perfume, MultipartFile file);
}
