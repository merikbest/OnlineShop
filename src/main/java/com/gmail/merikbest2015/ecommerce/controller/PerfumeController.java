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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.PERFUME;

@Controller
@RequiredArgsConstructor
@RequestMapping(PERFUME)
public class PerfumeController {

    private final PerfumeService perfumeService;

    @GetMapping("/{perfumeId}")
    public String getPerfumeById(@PathVariable("perfumeId") Long perfumeId, Model model) {
        Perfume perfume = perfumeService.getPerfumeById(perfumeId);
        model.addAttribute("perfume", perfume);
        return "product";
    }

    @GetMapping
    public String getPerfumes(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumes(pageable);
        processModel(model, perfumes, PERFUME);
        return "menu";
    }

    @GetMapping("/perfumer/{perfumer}")
    public String getPerfumesByPerfumer(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                        @PathVariable("perfumer") String perfumer, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumesByPerfumer(perfumer, pageable);
        processModel(model, perfumes, PERFUME + "/" + perfumer);
        return "menu";
    }

    @GetMapping("/gender/{gender}")
    public String getPerfumesByGender(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                      @PathVariable("gender") String perfumeGender, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumesByGender(perfumeGender, pageable);
        processModel(model, perfumes, PERFUME + "/gender/" + perfumeGender);
        return "menu";
    }

    @GetMapping("/search")
    public String searchByParameters(
            @PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
            @RequestParam(value = "gender", required = false, defaultValue = "") List<String> gender,
            @RequestParam(value = "perfumers", required = false, defaultValue = "") List<String> perfumers,
            @RequestParam(value = "startingPrice", required = false, defaultValue = "0") Integer startingPrice,
            @RequestParam(value = "endingPrice", required = false, defaultValue = "0") Integer endingPrice,
            Model model) {
        StringBuilder urlBuilder = new StringBuilder();
        Page<Perfume> perfumesSearch = null;
        getMinMaxPerfumePrice(model);

        if (gender.isEmpty() && perfumers.isEmpty()) {
            Page<Perfume> priceRange = perfumeService.findByPriceBetween(startingPrice, endingPrice, pageable);
            int[] pagination = ControllerUtils.computePagination(priceRange);

            model.addAttribute("pagination", pagination);
            model.addAttribute("url", PERFUME + "/search?startingPrice=" + startingPrice + "&endingPrice=" + endingPrice);
            model.addAttribute("page", priceRange);

            return "menu";
        }

        if (gender.isEmpty()) {
            perfumesSearch = perfumeService.findByPerfumerIn(perfumers, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(perfumers);
        } else if (perfumers.isEmpty()) {
            perfumesSearch = perfumeService.findByPerfumeGenderIn(gender, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(gender);
        } else if (!gender.isEmpty() && !perfumers.isEmpty()) {
            perfumesSearch = perfumeService.findByPerfumerInAndPerfumeGenderIn(perfumers, gender, pageable);
            List<String> urlArray = new ArrayList<String>(perfumers);
            urlArray.addAll(gender);
            urlBuilder = ControllerUtils.getUrlBuilder(urlArray);
        }

        int[] pagination = ControllerUtils.computePagination(perfumesSearch);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", PERFUME + "/search" + urlBuilder);
        model.addAttribute("page", perfumesSearch);

        return "menu";
    }

    private void processModel(Model model, Page<Perfume> perfumes, String url) {
        model.addAttribute("pagination", ControllerUtils.computePagination(perfumes));
        model.addAttribute("url", url);
        model.addAttribute("page", perfumes);
        model.addAttribute("minPerfumePrice", perfumeService.minPerfumePrice());
        model.addAttribute("maxPerfumePrice", perfumeService.maxPerfumePrice());
    }

    private void getMinMaxPerfumePrice(Model model) {
        model.addAttribute("minPerfumePrice", perfumeService.minPerfumePrice());
        model.addAttribute("maxPerfumePrice", perfumeService.maxPerfumePrice());
    }
}