package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PerfumeService perfumeService;

    @GetMapping
    public String home(Model model) {
        List<Perfume> perfumes = perfumeService.findPopularPerfumes();
        model.addAttribute("perfumes", perfumes);
        return "home";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "user/user-cabinet";
    }

    @GetMapping("/search")
    public String searchPerfumes(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                 @RequestParam String text, Model model) {
        Page<Perfume> perfumes = perfumeService.searchPerfumes(text, pageable);
        model.addAttribute("pagination", ControllerUtils.computePagination(perfumes));
        model.addAttribute("url", "/perfume");
        model.addAttribute("page", perfumes);
        return "menu";
    }
}
