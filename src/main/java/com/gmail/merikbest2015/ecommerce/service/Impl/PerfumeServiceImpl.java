package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.dto.PerfumeRequest;
import com.gmail.merikbest2015.ecommerce.domain.dto.PerfumeSearchRequest;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeService {

    @Value("${upload.path}")
    private String uploadPath;
    private final PerfumeRepository perfumeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.getOne(perfumeId);
    }

    @Override
    public List<Perfume> findPopularPerfumes() {
        List<Long> perfumeIds = Arrays.asList(26L, 43L, 46L, 106L, 34L, 76L, 82L, 85L, 27L, 39L, 79L, 86L);
        return perfumeRepository.findByIdIn(perfumeIds);
    }

    @Override
    public Page<Perfume> getPerfumes(Pageable pageable) {
        return perfumeRepository.findAll(pageable);
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
    public Page<Perfume> searchPerfumes(String text, Pageable pageable) {
        return perfumeRepository.findByPerfumerOrPerfumeTitle(text, text, pageable);
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
    public Page<Perfume> getPerfumesByFilterParams(PerfumeSearchRequest request, Pageable pageable) {
        return perfumeRepository.getPerfumesByFilterParams(
                request.getPerfumers(),
                request.getGenders(),
                request.getStartingPrice(),
                request.getEndingPrice(),
                pageable);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void savePerfume(PerfumeRequest perfumeRequest, MultipartFile file) {
        Perfume perfume = modelMapper.map(perfumeRequest, Perfume.class);
        saveFile(perfume, file);
        perfumeRepository.save(perfume);
    }

    private void saveFile(Perfume perfume, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            perfume.setFilename(resultFilename);
        }
    }
}
