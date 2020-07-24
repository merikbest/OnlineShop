package com.gmail.merikbest2015.ecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.util.Set;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {
    private boolean success;

    @JsonAlias("error-codes")
    private Set<String> errorCodes;
}
