package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/menu")
    public String main(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable, Model model) {
        Page<Perfume> page = perfumeService.findAll(pageable);
        int[] pagination = ControllerUtils.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu");
        model.addAttribute("page", page);

        return "menu";
    }

    @GetMapping("/menu/{perfumer}")
    public String findByPerfumer(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
                                 @PathVariable String perfumer, Model model
    ) {
        Page<Perfume> perfumeList = perfumeService.findByPerfumer(perfumer, pageable);
        int[] pagination = ControllerUtils.computePagination(perfumeList);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/" + perfumer);
        model.addAttribute("page", perfumeList);

        return "menu";
    }

    @GetMapping("/menu/gender/{gender}")
    public String findByPerfumeGender(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
                                      @PathVariable("gender") String perfumeGender, Model model
    ) {
        Page<Perfume> gender = perfumeService.findByPerfumeGender(perfumeGender, pageable);
        int[] pagination = ControllerUtils.computePagination(gender);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/gender/" + perfumeGender);
        model.addAttribute("page", gender);

        return "menu";
    }

    @GetMapping("/menu/search")
    public String searchByParameters(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
                                     @RequestParam String gender,
                                     @RequestParam List<String> perfumers, Model model
    ) {
        Page<Perfume> perfumesSearch = perfumeService.findByPerfumeGenderAndPerfumerIn(gender, perfumers, pageable);
        int[] pagination = ControllerUtils.computePagination(perfumesSearch);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/search");
        model.addAttribute("page", perfumesSearch);

        return "menu";
    }
}