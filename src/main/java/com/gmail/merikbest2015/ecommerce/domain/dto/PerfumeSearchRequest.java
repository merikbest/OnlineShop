package com.gmail.merikbest2015.ecommerce.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerfumeSearchRequest {
    private List<String> perfumers;
    private List<String> genders;
    private Integer startingPrice;
    private Integer endingPrice;
}
