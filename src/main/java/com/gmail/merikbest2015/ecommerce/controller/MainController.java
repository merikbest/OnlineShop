package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Perfume> iterable = perfumeService.findAll();
        model.addAttribute("perfumes", iterable);

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

    //ПАНЕЛЬ ПОИСКА
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
//        List<Perfume> perfumes = perfumeService.findByPerfumer(filter);
//        model.addAttribute("perfumes", perfumes);

        return "filter";
    }

    @GetMapping("/product/{perfume}")
    public String getProduct(@PathVariable Perfume perfume, Model model) {
        model.addAttribute("perfume" , perfume);

        return "product";
    }
}