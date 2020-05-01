package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {
    @Autowired
    private PerfumeRepository perfumeRepository;

    @GetMapping("/menu")
    public String main(Model model) {
        Iterable<Perfume> perfumes = perfumeRepository.findAll();
        model.addAttribute("perfumes", perfumes);
        return "menu";
    }

    @GetMapping("/menu/{perfumer}")
    public String findByPerfumer(@PathVariable String perfumer, Model model) {
        List<Perfume> perfumeList = perfumeRepository.findByPerfumer(perfumer);
        model.addAttribute("perfumes", perfumeList);
        return "menu";
    }

    @GetMapping("/menu/gender/{gender}")
    public String findByPerfumeGender(@PathVariable("gender") String perfumeGender, Model model) {
        List<Perfume> gender = perfumeRepository.findByPerfumeGender(perfumeGender);
        model.addAttribute("gender", gender);
        return "menu";
    }
}
