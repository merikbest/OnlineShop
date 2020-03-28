package com.gmail.merikbest2015.ecommerce.repos;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

//Spring автоматически реализует этот интерфейс хранилища в bean-компоненте с тем же именем
// (с изменением в регистре - он называется userRepository)
public interface PerfumeRepository extends CrudRepository<Perfume, Integer> {
    List<Perfume> findByPerfumer(String perfumer);
}
