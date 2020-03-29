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
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "perfumeTitle") String perfumeTitle,
                      @RequestParam(name = "perfumer")  String perfumer,
                      @RequestParam(name = "year")  Integer year,
                      @RequestParam(name = "country")  String country,
                      @RequestParam(name = "perfumeGender")  String perfumeGender,
                      @RequestParam(name = "fragranceTopNotes")  String fragranceTopNotes,
                      @RequestParam(name = "fragranceMiddleNotes")  String fragranceMiddleNotes,
                      @RequestParam(name = "fragranceBaseNotes")  String fragranceBaseNotes,
                      @RequestParam(name = "description")  String description,
                      Map<String, Object> model)
    {
        Perfume perfume = new Perfume(perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes,
                fragranceMiddleNotes, fragranceBaseNotes, description);
        perfumeRepository.save(perfume);
        return "addToBD";
    }

    @GetMapping("/add")
    public String addToBD() {
        return "addToBD";
    }

    @GetMapping("/list")
    public String get(Map<String, Object> model) {
        Iterable<Perfume> perfumes = perfumeRepository.findAll();
        model.put("perfumes", perfumes);
        return "list";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Perfume> perfumes = perfumeRepository.findByPerfumer(filter);
        model.put("perfumes", perfumes);
        return "filter";
    }
}