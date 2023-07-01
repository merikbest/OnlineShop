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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.gmail.merikbest2015.ecommerce.util.TestConstants.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-perfumes-before.sql", "/sql/create-user-before.sql", "/sql/create-orders-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-orders-after.sql", "/sql/create-user-after.sql", "/sql/create-perfumes-after.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[200] GET /user/contacts - Contacts")
    public void contacts() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/contacts"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.CONTACTS));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/reset - Password Reset")
    public void passwordReset() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/reset"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_PASSWORD_RESET));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/account - User Account")
    public void userAccount() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/account"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_ACCOUNT))
                .andExpect(model().attribute("user", hasProperty("id", is(USER_ID))))
                .andExpect(model().attribute("user", hasProperty("email", is(USER_EMAIL))))
                .andExpect(model().attribute("user", hasProperty("firstName", is(USER_FIRST_NAME))))
                .andExpect(model().attribute("user", hasProperty("lastName", is(USER_LAST_NAME))))
                .andExpect(model().attribute("user", hasProperty("city", is(USER_CITY))))
                .andExpect(model().attribute("user", hasProperty("address", is(USER_ADDRESS))))
                .andExpect(model().attribute("user", hasProperty("phoneNumber", is(USER_PHONE_NUMBER))))
                .andExpect(model().attribute("user", hasProperty("postIndex", is(USER_POST_INDEX))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/orders/search - Search Orders By Email")
    public void searchUserOrders_ByEmail() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/orders/search")
                        .param("searchType", "email")
                        .param("text", USER_EMAIL))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERS))
                .andExpect(model().attribute("page", hasProperty("content", hasSize(1))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/orders/search - Search Orders bt First Name")
    public void searchUserOrders_ByFirstName() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/orders/search")
                        .param("searchType", "firstName")
                        .param("text", USER_FIRST_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERS))
                .andExpect(model().attribute("page", hasProperty("content", hasSize(1))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/orders/search - Search Orders By Last Name")
    public void searchUserOrders_ByLastName() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/orders/search")
                        .param("searchType", "lastName")
                        .param("text", USER_LAST_NAME))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERS))
                .andExpect(model().attribute("page", hasProperty("content", hasSize(1))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/info - User Info")
    public void userInfo() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/info"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_INFO))
                .andExpect(model().attribute("user", hasProperty("id", is(USER_ID))))
                .andExpect(model().attribute("user", hasProperty("email", is(USER_EMAIL))))
                .andExpect(model().attribute("user", hasProperty("firstName", is(USER_FIRST_NAME))))
                .andExpect(model().attribute("user", hasProperty("lastName", is(USER_LAST_NAME))))
                .andExpect(model().attribute("user", hasProperty("city", is(USER_CITY))))
                .andExpect(model().attribute("user", hasProperty("address", is(USER_ADDRESS))))
                .andExpect(model().attribute("user", hasProperty("phoneNumber", is(USER_PHONE_NUMBER))))
                .andExpect(model().attribute("user", hasProperty("postIndex", is(USER_POST_INDEX))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /user/info/edit - Get Edit User Info Page")
    public void getEditUserInfoPage() throws Exception {
        mockMvc.perform(get(PathConstants.USER + "/info/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_INFO_EDIT))
                .andExpect(model().attribute("user", hasProperty("id", is(USER_ID))))
                .andExpect(model().attribute("user", hasProperty("email", is(USER_EMAIL))))
                .andExpect(model().attribute("user", hasProperty("firstName", is(USER_FIRST_NAME))))
                .andExpect(model().attribute("user", hasProperty("lastName", is(USER_LAST_NAME))))
                .andExpect(model().attribute("user", hasProperty("city", is(USER_CITY))))
                .andExpect(model().attribute("user", hasProperty("address", is(USER_ADDRESS))))
                .andExpect(model().attribute("user", hasProperty("phoneNumber", is(USER_PHONE_NUMBER))))
                .andExpect(model().attribute("user", hasProperty("postIndex", is(USER_POST_INDEX))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[300] POST /user/info/edit - Edit User Info")
    public void editUserInfo() throws Exception {
        mockMvc.perform(post(PathConstants.USER + "/info/edit")
                        .param("firstName", USER_FIRST_NAME)
                        .param("lastName", USER_LAST_NAME)
                        .param("city", USER_CITY)
                        .param("address", USER_ADDRESS)
                        .param("phoneNumber", USER_PHONE_NUMBER)
                        .param("postIndex", USER_POST_INDEX))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/info"))
                .andExpect(flash().attribute("messageType", "alert-success"))
                .andExpect(flash().attribute("message", SuccessMessage.USER_UPDATED));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /user/info/edit - Edit User Info Return Input Errors")
    public void editUserInfo_ReturnInputErrors() throws Exception {
        mockMvc.perform(post(PathConstants.USER + "/info/edit")
                        .param("email", USER_EMAIL)
                        .param("firstName", "")
                        .param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_INFO_EDIT))
                .andExpect(model().attribute("firstNameError", is(ErrorMessage.EMPTY_FIRST_NAME)))
                .andExpect(model().attribute("lastNameError", is(ErrorMessage.EMPTY_LAST_NAME)));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /user/change/password - Change Password")
    public void changePassword() throws Exception {
        mockMvc.perform(post(PathConstants.USER + "/change/password")
                        .param("password", "password")
                        .param("password2", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_PASSWORD_RESET))
                .andExpect(model().attribute("messageType", "alert-success"))
                .andExpect(model().attribute("message", SuccessMessage.PASSWORD_CHANGED));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /user/change/password - Change Password Return Input Errors")
    public void changePassword_ReturnInputErrors() throws Exception {
        mockMvc.perform(post(PathConstants.USER + "/change/password")
                        .param("password", "")
                        .param("password2", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_PASSWORD_RESET))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORD_CHARACTER_LENGTH)))
                .andExpect(model().attribute("password2Error", is(ErrorMessage.PASSWORD2_CHARACTER_LENGTH)));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /user/change/password - Change Password Return Password Not Match Error")
    public void changePassword_ReturnPasswordNotMatchError() throws Exception {
        mockMvc.perform(post(PathConstants.USER + "/change/password")
                        .param("password", "password")
                        .param("password2", "password2"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.USER_PASSWORD_RESET))
                .andExpect(model().attribute("passwordError", is(ErrorMessage.PASSWORDS_DO_NOT_MATCH)));
    }
}
