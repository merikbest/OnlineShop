package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/menu")
    public String main(Model model) {
        Iterable<Perfume> perfumes = perfumeService.findAll();
        model.addAttribute("perfumes", perfumes);
        return "menu";
    }

    @GetMapping("/menu/{perfumer}")
    public String findByPerfumer(@PathVariable String perfumer, Model model) {
        List<Perfume> perfumeList = perfumeService.findByPerfumer(perfumer);
        model.addAttribute("perfumes", perfumeList);
        return "menu";
    }

    @GetMapping("/menu/gender/{gender}")
    public String findByPerfumeGender(@PathVariable("gender") String perfumeGender, Model model) {
        List<Perfume> gender = perfumeService.findByPerfumeGender(perfumeGender);
        model.addAttribute("gender", gender);
        return "menu";
    }
}
