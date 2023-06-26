package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.request.PerfumeRequest;
import com.gmail.merikbest2015.ecommerce.dto.request.PerfumeSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerfumeService {

    Perfume getPerfumeById(Long perfumeId);

    List<Perfume> getPopularPerfumes();

    Page<Perfume> getPerfumes(Pageable pageable);

    Page<Perfume> getPerfumesByFilterParams(PerfumeSearchRequest searchRequest, Pageable pageable);

    Page<Perfume> searchPerfumes(PerfumeSearchRequest searchRequest, Pageable pageable);

    void savePerfume(PerfumeRequest perfume, MultipartFile file);
}
