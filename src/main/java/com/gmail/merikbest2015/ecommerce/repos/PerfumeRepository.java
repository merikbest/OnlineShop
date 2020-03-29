package com.gmail.merikbest2015.ecommerce.repos;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerfumeRepository extends CrudRepository<Perfume, Integer> {
    List<Perfume> findByPerfumer(String perfumer);
}