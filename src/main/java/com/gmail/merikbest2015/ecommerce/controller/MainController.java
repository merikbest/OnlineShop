package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
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
    private final PerfumeService perfumeService;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    public MainController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

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
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        List<Perfume> perfumes = perfumeRepository.findByPerfumerOrPerfumeTitle(filter, filter);

//        Iterable<Perfume> perfumes = perfumeRepository.findAll();
//
//        if (filter != null && !filter.isEmpty()) {
//            perfumes = perfumeRepository.findByPerfumer(filter);
//        }
        Iterable<Perfume> perfumes = perfumeRepository.findByPerfumer(filter);



        model.addAttribute("perfumes", perfumes);

        return "filter";
    }

//    @GetMapping("/filter")
//    public String getFilterResult() {
//        return "filter";
//    }

    @GetMapping("/product/{perfume}")
    public String getProduct(@PathVariable Perfume perfume, Model model) {
        model.addAttribute("perfume", perfume);

        return "product";
    }
}