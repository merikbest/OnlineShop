package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    List<Perfume> findByIdIn(List<Long> perfumesIds);

    Page<Perfume> findAll(Pageable pageable);

    Page<Perfume> findByPerfumer(String perfumer, Pageable pageable);

    Page<Perfume> findByPerfumeGender(String perfumeGender, Pageable pageable);

    Page<Perfume> findByPerfumerOrPerfumeTitle(String perfumer, String perfumeTitle, Pageable pageable);

    @Query(value = "SELECT min(price) FROM Perfume ")
    BigDecimal minPerfumePrice();

    @Query(value = "SELECT max(price) FROM Perfume ")
    BigDecimal maxPerfumePrice();

    @Query("SELECT perfume FROM Perfume perfume " +
            "WHERE (coalesce(:perfumers, null) IS NULL OR perfume.perfumer IN :perfumers) " +
            "AND (coalesce(:genders, null) IS NULL OR perfume.perfumeGender IN :genders) " +
            "AND (coalesce(:priceStart, null) IS NULL OR perfume.price BETWEEN :priceStart AND :priceEnd)")
    Page<Perfume> getPerfumesByFilterParams(
            List<String> perfumers,
            List<String> genders,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);
}
