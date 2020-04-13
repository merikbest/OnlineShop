package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private PerfumeRepository perfumeRepository;

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Perfume> perfumes = perfumeRepository.findAll();
        model.put("perfumes", perfumes);
        return "main";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "userCabinet";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Perfume> perfumes = perfumeRepository.findByPerfumer(filter);
        model.put("perfumes", perfumes);
        return "filter";
    }
}