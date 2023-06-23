package com.gmail.merikbest2015.ecommerce.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class OrderRequest {
    private Long id;
    private Double totalPrice;
    private LocalDate date;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String firstName;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String lastName;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String city;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String address;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @NotNull(message = "Почтовый индекс не может быть пустым")
    @Min(value = 5, message = "Почтовый индекс должен содержать 5 цифр")
    private Integer postIndex;
}
