package com.gmail.merikbest2015.ecommerce.utils;

import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.request.PerfumeSearchRequest;
import com.gmail.merikbest2015.ecommerce.dto.response.MessageResponse;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ControllerUtils {

    private final PerfumeService perfumeService;

    public boolean validateInputField(Model model, MessageResponse messageResponse, String attributeKey, Object attributeValue) {
        if (!messageResponse.getResponse().contains("success")) {
            model.addAttribute(messageResponse.getResponse(), messageResponse.getMessage());
            model.addAttribute(attributeKey, attributeValue);
            return true;
        }
        return false;
    }

    public boolean validateInputFields(BindingResult bindingResult, Model model, String attributeKey, Object attributeValue) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
            model.addAttribute(attributeKey, attributeValue);
            return true;
        }
        return false;
    }

    public Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public void processModel(PerfumeSearchRequest request, Model model, Page<Perfume> perfumes) {
        model.addAttribute("searchRequest", request);
        model.addAttribute("pagination", computePagination(perfumes));
        model.addAttribute("page", perfumes);
    }

    public String processAlertMessage(Model model, String page, MessageResponse messageResponse) {
        model.addAttribute("messageType", messageResponse.getResponse());
        model.addAttribute("message", messageResponse.getMessage());
        return page;
    }

    public String processAlertMessageAndRedirect(RedirectAttributes redirectAttributes, MessageResponse messageResponse) {
        redirectAttributes.addFlashAttribute("messageType", messageResponse.getResponse());
        redirectAttributes.addFlashAttribute("message", messageResponse.getMessage());
        return "redirect:/" + Pages.LOGIN;
    }

    private int[] computePagination(Page<?> page) {
        Integer totalPages = page.getTotalPages();
        if (totalPages > 7) {
            Integer pageNumber = page.getNumber() + 1;
            Integer[] head = pageNumber > 4 ? new Integer[]{1, -1} : new Integer[]{1, 2, 3};
            Integer[] tail = pageNumber < (totalPages - 3) ? new Integer[]{-1, totalPages} : new Integer[]{totalPages - 2, totalPages - 1, totalPages};
            Integer[] bodyBefore = (pageNumber > 4 && pageNumber < (totalPages - 1)) ? new Integer[]{pageNumber - 2, pageNumber - 1} : new Integer[]{};
            Integer[] bodyAfter = (pageNumber > 2 && pageNumber < (totalPages - 3)) ? new Integer[]{pageNumber + 1, pageNumber + 2} : new Integer[]{};

            List<Integer> list = new ArrayList<>();
            Collections.addAll(list, head);
            Collections.addAll(list, bodyBefore);
            Collections.addAll(list, (pageNumber > 3 && pageNumber < totalPages - 2) ? new Integer[]{pageNumber} : new Integer[]{});
            Collections.addAll(list, bodyAfter);
            Collections.addAll(list, tail);
            Integer[] arr = list.toArray(new Integer[0]);
            return Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
        } else {
            return IntStream.rangeClosed(1, totalPages).toArray();
        }
    }
}
