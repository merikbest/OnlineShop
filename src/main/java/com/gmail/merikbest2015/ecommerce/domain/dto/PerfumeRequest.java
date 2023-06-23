package com.gmail.merikbest2015.ecommerce.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PerfumeRequest {
    private Long id;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String perfumeTitle;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String perfumer;

    @NotNull(message = "Пожалуйста заполните поле")
    private Integer year;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String country;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String perfumeGender;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String fragranceTopNotes;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String fragranceMiddleNotes;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String fragranceBaseNotes;

    private String description;
    private String filename;

    @NotNull(message = "Пожалуйста заполните поле")
    private Integer price;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String volume;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String type;
}
