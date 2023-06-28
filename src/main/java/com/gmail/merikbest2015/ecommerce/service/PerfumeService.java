package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PerfumeService {

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPopularPerfumes();

    Page<Perfume> getPerfumesByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Perfume> searchPerfumes(SearchRequest searchRequest, Pageable pageable);
}
