package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.constants.PathConstants;
import com.gmail.merikbest2015.ecommerce.constants.SuccessMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.gmail.merikbest2015.ecommerce.util.TestConstants.USER_EMAIL;
import static com.gmail.merikbest2015.ecommerce.util.TestConstants.USER_FIRST_NAME;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-perfumes-before.sql", "/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-user-after.sql", "/sql/create-perfumes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[200] GET /registration - Get Registration Page")
    public void getRegistrationPage() throws Exception {
        mockMvc.perform(get(PathConstants.REGISTRATION))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.REGISTRATION));
    }

    @Test
    @DisplayName("[200] POST /registration - Registration Return Input Errors")
    public void registration_ReturnInputErrors() throws Exception {
        mockMvc.perform(post(PathConstants.REGISTRATION)
                        .param("g-recaptcha-response", "")
                        .param("email", "")
                        .param("firstName", "")
                        .param("password", "test")
                        .param("password2", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.REGISTRATION))
                .andExpect(model().attribute("emailError", is(ErrorMessage.EMAIL_CANNOT_BE_EMPTY)))
                .andExpect(model().attribute("firstNameError", is(ErrorMessage.EMPTY_FIRST_NAME)))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORD_CHARACTER_LENGTH)))
                .andExpect(model().attribute("password2Error", is(ErrorMessage.PASSWORD2_CHARACTER_LENGTH)));
    }

    @Test
    @DisplayName("[200] POST /registration - Registration Passwords Not Match")
    public void registration_PasswordsNotMatch() throws Exception {
        mockMvc.perform(post(PathConstants.REGISTRATION)
                        .param("g-recaptcha-response", "")
                        .param("email", USER_EMAIL)
                        .param("firstName", USER_FIRST_NAME)
                        .param("password", "password")
                        .param("password2", "password2"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.REGISTRATION))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORDS_DO_NOT_MATCH)));
    }

    @Test
    @DisplayName("[200] POST /registration - Registration Email Error")
    public void registration_EmailError() throws Exception {
        mockMvc.perform(post(PathConstants.REGISTRATION)
                        .param("g-recaptcha-response", "")
                        .param("email", USER_EMAIL)
                        .param("firstName", USER_FIRST_NAME)
                        .param("password", "password")
                        .param("password2", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.REGISTRATION))
                .andExpect(model().attribute("emailError", is(ErrorMessage.EMAIL_IN_USE)));
    }

    @Test
    @DisplayName("[200] POST /registration - Registration Captcha Error")
    public void registration_CaptchaError() throws Exception {
        mockMvc.perform(post(PathConstants.REGISTRATION)
                        .param("g-recaptcha-response", "")
                        .param("email", "test1234@test.com")
                        .param("firstName", USER_FIRST_NAME)
                        .param("password", "password")
                        .param("password2", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.REGISTRATION))
                .andExpect(model().attribute("captchaError", is(ErrorMessage.CAPTCHA_ERROR)));
    }

    @Test
    @DisplayName("[200] GET /registration/activate/{code} - Activate Email Code")
    public void activateEmailCode() throws Exception {
        mockMvc.perform(get(PathConstants.REGISTRATION + "/activate/8e97dc37-2cf5-47e2-98e0"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.LOGIN))
                .andExpect(model().attribute("messageType", "alert-success"))
                .andExpect(model().attribute("message", SuccessMessage.USER_ACTIVATED));
    }

    @Test
    @DisplayName("[200] GET /registration/activate/{code} - Activate Email Code Not Found")
    public void activateEmailCode_NotFound() throws Exception {
        mockMvc.perform(get(PathConstants.REGISTRATION + "/activate/123"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.LOGIN))
                .andExpect(model().attribute("messageType", "alert-danger"))
                .andExpect(model().attribute("message", ErrorMessage.ACTIVATION_CODE_NOT_FOUND));
    }
}
