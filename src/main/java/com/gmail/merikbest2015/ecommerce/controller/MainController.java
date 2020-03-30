package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private PerfumeRepository perfumeRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
                      @RequestParam(name = "perfumer") String perfumer,
                      @RequestParam(name = "year") Integer year,
                      @RequestParam(name = "country") String country,
                      @RequestParam(name = "perfumeGender") String perfumeGender,
                      @RequestParam(name = "fragranceTopNotes") String fragranceTopNotes,
                      @RequestParam(name = "fragranceMiddleNotes") String fragranceMiddleNotes,
                      @RequestParam(name = "fragranceBaseNotes") String fragranceBaseNotes,
                      @RequestParam(name = "description") String description,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model
    ) throws IOException {
        Perfume perfume = new Perfume(perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes,
                fragranceMiddleNotes, fragranceBaseNotes, description);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            perfume.setFilename(resultFilename);
        }
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