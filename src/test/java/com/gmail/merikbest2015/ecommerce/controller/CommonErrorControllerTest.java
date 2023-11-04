package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.constants.PathConstants;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommonErrorControllerTest {

    private CommonErrorController commonErrorController = new CommonErrorController();

    @Test
    void handleError_WhenStatusCodeNotFound_ShouldReturnError404Page() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(HttpStatus.NOT_FOUND.value());
        when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Not Found");

        String result = commonErrorController.handleError(request, model);

        assertEquals(Pages.ERROR_404, result);
        verify(model).addAttribute("errorMessage", "Not Found");
    }

    @Test
    void handleError_WhenStatusCodeNotNotFound_ShouldReturnError500Page() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());

        String result = commonErrorController.handleError(request, model);

        assertEquals(Pages.ERROR_500, result);
        verify(model, never()).addAttribute(eq("errorMessage"), any());
    }

    @Test
    void getErrorPath_ShouldReturnErrorPathConstant() {
        String errorPath = commonErrorController.getErrorPath();
        assertEquals(PathConstants.ERROR, errorPath);
    }
}
