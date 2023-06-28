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
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-perfumes-before.sql", "/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-user-after.sql", "/sql/create-perfumes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[200] GET /auth/forgot - Get Forgot Password Page")
    public void getForgotPasswordPage() throws Exception {
        mockMvc.perform(get(PathConstants.AUTH + "/forgot"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.FORGOT_PASSWORD));
    }

    @Test
    @DisplayName("[200] POST /auth/forgot - Forgot Password")
    public void forgotPassword() throws Exception {
        mockMvc.perform(post(PathConstants.AUTH + "/forgot")
                        .param("email", USER_EMAIL))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.FORGOT_PASSWORD))
                .andExpect(model().attribute("messageType", "alert-success"))
                .andExpect(model().attribute("message", SuccessMessage.PASSWORD_CODE_SEND));
    }

    @Test
    @DisplayName("[200] POST /auth/forgot - Forgot Password Email Not Found")
    public void forgotPassword_EmailNotFound() throws Exception {
        mockMvc.perform(post(PathConstants.AUTH + "/forgot")
                        .param("email", "email"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.FORGOT_PASSWORD))
                .andExpect(model().attribute("messageType", "alert-danger"))
                .andExpect(model().attribute("message", ErrorMessage.EMAIL_NOT_FOUND));
    }

    @Test
    @DisplayName("[200] GET /auth/reset/{code} - Get Reset Password")
    public void getResetPassword() throws Exception {
        mockMvc.perform(get(PathConstants.AUTH + "/reset/3f9bcdb0-2241-4c34-803e-598b497d571f"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.RESET_PASSWORD))
                .andExpect(model().attribute("email", "helloworld@test.com"));
    }

    @Test
    @DisplayName("[404] GET /auth/reset/{code} - Get Reset Password Not Found")
    public void getResetPassword_NotFound() throws Exception {
        mockMvc.perform(get(PathConstants.AUTH + "/reset/" + USER_EMAIL))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(ErrorMessage.INVALID_PASSWORD_CODE));
    }

    @Test
    @DisplayName("[300] POST /auth/reset - Reset Password")
    public void resetPassword() throws Exception {
        mockMvc.perform(post(PathConstants.AUTH + "/reset")
                        .param("email", USER_EMAIL)
                        .param("password", "password")
                        .param("password2", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(PathConstants.LOGIN))
                .andExpect(flash().attribute("messageType", "alert-success"))
                .andExpect(flash().attribute("message", SuccessMessage.PASSWORD_CHANGED));
    }

    @Test
    @DisplayName("[200] POST /auth/reset - Reset Password Return Input Errors")
    public void resetPassword_ReturnInputErrors() throws Exception {
        mockMvc.perform(post(PathConstants.AUTH + "/reset")
                        .param("email", USER_EMAIL)
                        .param("password", "")
                        .param("password2", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.RESET_PASSWORD))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORD_CHARACTER_LENGTH)))
                .andExpect(model().attribute("password2Error", is(ErrorMessage.PASSWORD2_CHARACTER_LENGTH)));
    }

    @Test
    @DisplayName("[200] POST /auth/reset - Reset Password Return Password Not Match Error")
    public void resetPassword_ReturnPasswordNotMatchError() throws Exception {
        mockMvc.perform(post(PathConstants.AUTH + "/reset")
                        .param("email", USER_EMAIL)
                        .param("password", "password")
                        .param("password2", "password2"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.RESET_PASSWORD))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORDS_DO_NOT_MATCH)))
                .andExpect(model().attribute("email", is(USER_EMAIL)));
    }
}
