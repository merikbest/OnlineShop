package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.constants.PathConstants;
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
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /order/{orderId} - Get Order")
    public void getOrder() throws Exception {
        mockMvc.perform(get(PathConstants.ORDER + "/{orderId}", 111))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDER))
                .andExpect(model().attribute("order", hasProperty("id", is(ORDER_ID))))
                .andExpect(model().attribute("order", hasProperty("totalPrice", is(ORDER_TOTAL_PRICE))))
                .andExpect(model().attribute("order", hasProperty("firstName", is(ORDER_FIRST_NAME))))
                .andExpect(model().attribute("order", hasProperty("lastName", is(ORDER_LAST_NAME))))
                .andExpect(model().attribute("order", hasProperty("city", is(ORDER_CITY))))
                .andExpect(model().attribute("order", hasProperty("address", is(ORDER_ADDRESS))))
                .andExpect(model().attribute("order", hasProperty("email", is(ORDER_EMAIL))))
                .andExpect(model().attribute("order", hasProperty("phoneNumber", is(ORDER_PHONE_NUMBER))))
                .andExpect(model().attribute("order", hasProperty("postIndex", is(ORDER_POST_INDEX))))
                .andExpect(model().attribute("order", hasProperty("perfumes", hasSize(2))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[404] GET /order/{orderId} - Get Order Not Found")
    public void getOrder_NotFound() throws Exception {
        mockMvc.perform(get(PathConstants.ORDER + "/{orderId}", 222))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /order - Get Ordering")
    public void getOrdering() throws Exception {
        mockMvc.perform(get(PathConstants.ORDER))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERING))
                .andExpect(model().attribute("perfumes", hasSize(2)));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] GET /order/user/orders - Get User Orders List")
    public void getUserOrdersList() throws Exception {
        mockMvc.perform(get(PathConstants.ORDER + "/user/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERS))
                .andExpect(model().attribute("page", hasProperty("content", hasSize(1))));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /order - Post Order")
    public void postOrder() throws Exception {
        mockMvc.perform(post(PathConstants.ORDER)
                        .param("firstName", ORDER_FIRST_NAME)
                        .param("lastName", ORDER_LAST_NAME)
                        .param("city", ORDER_CITY)
                        .param("address", ORDER_ADDRESS)
                        .param("email", ORDER_EMAIL)
                        .param("phoneNumber", ORDER_PHONE_NUMBER)
                        .param("postIndex", String.valueOf(ORDER_POST_INDEX))
                        .param("totalPrice", String.valueOf(171)))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDER_FINALIZE));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    @DisplayName("[200] POST /order - Post Order Return Input Errors")
    public void postOrder_ReturnInputErrors() throws Exception {
        mockMvc.perform(post(PathConstants.ORDER)
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("city", "")
                        .param("address", "")
                        .param("email", "")
                        .param("phoneNumber", "")
                        .param("postIndex", "0")
                        .param("totalPrice", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.ORDERING))
                .andExpect(model().attribute("firstNameError", is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)))
                .andExpect(model().attribute("lastNameError", is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)))
                .andExpect(model().attribute("cityError", is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)))
                .andExpect(model().attribute("addressError", is(ErrorMessage.FILL_IN_THE_INPUT_FIELD)))
                .andExpect(model().attribute("emailError", is(ErrorMessage.EMAIL_CANNOT_BE_EMPTY)))
                .andExpect(model().attribute("phoneNumberError", is(ErrorMessage.EMPTY_PHONE_NUMBER)))
                .andExpect(model().attribute("postIndexError", is(ErrorMessage.EMPTY_POST_INDEX)));
    }
}
