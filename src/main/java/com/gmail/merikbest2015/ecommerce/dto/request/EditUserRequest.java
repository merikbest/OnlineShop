package com.gmail.merikbest2015.ecommerce.dto.request;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditUserRequest {

    @NotBlank(message = ErrorMessage.EMPTY_FIRST_NAME)
    private String firstName;

    @NotBlank(message = ErrorMessage.EMPTY_LAST_NAME)
    private String lastName;

    private String city;
    private String address;
    private String phoneNumber;
    private String postIndex;
    private String email;
}
