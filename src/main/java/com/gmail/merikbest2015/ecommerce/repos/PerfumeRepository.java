package com.gmail.merikbest2015.ecommerce.repos;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PerfumeRepository extends CrudRepository<Perfume, Long> {
    Page<Perfume> findAll(Pageable pageable);

    Page<Perfume> findByPerfumer(String perfumer, Pageable pageable);

    Page<Perfume> findByPerfumeGender(String perfumeGender, Pageable pageable);

    Page<Perfume> findByPerfumeGenderIn(List<String> perfumeGender, Pageable pageable);

    List<Perfume> findByPerfumerOrPerfumeTitle(String perfumer, String perfumeTitle);

    Iterable<Perfume> findByPerfumer(String perfumer);

    Page<Perfume> findByPerfumerInAndPerfumeGenderIn(List<String> perfumers, List<String> gender, Pageable pageable);

    Page<Perfume> findByPerfumerInOrPerfumeGenderIn (List<String> perfumers, List<String> gender, Pageable pageable);

    Page<Perfume> findByPerfumerIn (List<String> perfumers, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Perfume p set p.perfumeTitle = ?1, p.perfumer = ?2, p.year = ?3, p.country = ?4, " +
            "p.perfumeGender = ?5, p.fragranceTopNotes = ?6, p.fragranceMiddleNotes = ?7, p.fragranceBaseNotes = ?8," +
            "p.description = ?9, p.filename = ?10, p.price = ?11, p.volume = ?12, p.type = ?13  where p.id = ?14")
    void saveProductInfoById(String perfumeTitle, String perfumer, Integer year, String country, String perfumeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);
}