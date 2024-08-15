package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.constants.PathConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonErrorController implements ErrorController {

    @RequestMapping(PathConstants.ERROR)
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
            return Pages.ERROR_404;
        }
        return Pages.ERROR_500;
    }

//    @Override
//    public String getErrorPath() {
//        return PathConstants.ERROR;
//    }
}
