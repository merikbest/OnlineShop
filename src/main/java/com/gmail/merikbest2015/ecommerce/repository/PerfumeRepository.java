package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    List<Perfume> findByIdIn(List<Long> perfumesIds);

    Page<Perfume> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("SELECT perfume FROM Perfume perfume WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'perfumeTitle' THEN UPPER(perfume.perfumeTitle) " +
            "   WHEN :searchType = 'country' THEN UPPER(perfume.country) " +
            "   ELSE UPPER(perfume.perfumer) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY perfume.price ASC")
    Page<Perfume> searchPerfumes(String searchType, String text, Pageable pageable);

    @Query("SELECT perfume FROM Perfume perfume " +
            "WHERE (coalesce(:perfumers, null) IS NULL OR perfume.perfumer IN :perfumers) " +
            "AND (coalesce(:genders, null) IS NULL OR perfume.perfumeGender IN :genders) " +
            "AND (coalesce(:priceStart, null) IS NULL OR perfume.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY perfume.price ASC")
    Page<Perfume> getPerfumesByFilterParams(
            List<String> perfumers,
            List<String> genders,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);
}
