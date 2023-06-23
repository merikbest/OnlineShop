package com.gmail.merikbest2015.ecommerce.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    @Email(message = "Неправильный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @NotBlank(message = "Подтверждение пароля не может быть пустым")
    private String password2;
}
