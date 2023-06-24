package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.PerfumeSearchRequest;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.PERFUME;

@Controller
@RequiredArgsConstructor
@RequestMapping(PERFUME)
public class PerfumeController {

    private final PerfumeService perfumeService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{perfumeId}")
    public String getPerfumeById(@PathVariable("perfumeId") Long perfumeId, Model model) {
        model.addAttribute("perfume", perfumeService.getPerfumeById(perfumeId));
        return "perfume";
    }

    @GetMapping
    public String getPerfumes(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumes(pageable);
        controllerUtils.processModel(model, PERFUME + "/", perfumes);
        return "menu";
    }

    @GetMapping("/perfumer/{perfumer}")
    public String getPerfumesByPerfumer(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                        @PathVariable("perfumer") String perfumer, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumesByPerfumer(perfumer, pageable);
        controllerUtils.processModel(model, PERFUME + "/" + perfumer, perfumes);
        return "menu";
    }

    @GetMapping("/gender/{gender}")
    public String getPerfumesByGender(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                      @PathVariable("gender") String gender, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumesByGender(gender, pageable);
        controllerUtils.processModel(model, PERFUME + "/gender/" + gender, perfumes);
        return "menu";
    }

    @PostMapping("/filter")
    public String getPerfumesByFilterParams(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                            PerfumeSearchRequest searchRequest, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumesByFilterParams(searchRequest, pageable);
        controllerUtils.processModel(model, PERFUME + "/search", perfumes);
        return "menu";
    }

    @GetMapping("/search")
    public String searchPerfumes(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable,
                                 @RequestParam String text, Model model) {
        Page<Perfume> perfumes = perfumeService.searchPerfumes(text, pageable);
        controllerUtils.processModel(model, "/perfume", perfumes);
        return "menu";
    }
}
