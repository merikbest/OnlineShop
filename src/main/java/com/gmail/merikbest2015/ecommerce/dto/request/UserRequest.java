package com.gmail.merikbest2015.ecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.gmail.merikbest2015.ecommerce.constants.ErrorMessage.*;

@Data
public class UserRequest {

    @Email(message = INCORRECT_EMAIL)
    @NotBlank(message = EMAIL_CANNOT_BE_EMPTY)
    private String email;

    @NotBlank(message = EMPTY_FIRST_NAME)
    private String username;

    @Size(min = 6, max = 16, message = PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = PASSWORD2_CHARACTER_LENGTH)
    private String password2;
}
