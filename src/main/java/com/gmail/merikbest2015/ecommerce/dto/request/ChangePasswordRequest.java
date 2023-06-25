package com.gmail.merikbest2015.ecommerce.dto.request;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequest {

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD2_CHARACTER_LENGTH)
    private String password2;
}
