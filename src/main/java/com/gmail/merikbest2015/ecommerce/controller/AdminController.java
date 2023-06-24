package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.Role;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.PerfumeRequest;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

import static com.gmail.merikbest2015.ecommerce.constants.PathConstants.ADMIN;

@Controller
@RequestMapping(ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final PerfumeService perfumeService;
    private final UserService userService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/perfumes")
    public String getPerfumes(@PageableDefault(sort = {"id"}, size = 12) Pageable pageable, Model model) {
        Page<Perfume> perfumes = perfumeService.getPerfumes(pageable);
        controllerUtils.processModel(model, ADMIN + "/perfumes", perfumes);
        return "admin/perfumes";
    }

    @GetMapping("/perfume/{perfumeId}")
    public String getPerfume(@PathVariable("perfumeId") Long perfumeId, Model model) {
        model.addAttribute("perfume", perfumeService.getPerfumeById(perfumeId));
        return "admin/edit-perfume";
    }

    @PostMapping("/edit/perfume")
    public String editPerfume(@Valid PerfumeRequest perfume, BindingResult bindingResult, Model model,
                              @RequestParam("file") MultipartFile file) {
        System.out.println(perfume.toString());
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("perfume", perfume);
            return "admin/edit-perfume";
        }
        perfumeService.savePerfume(perfume, file);
        return "redirect:/admin/perfumes";
    }

    @GetMapping("/add/perfume")
    public String addPerfume() {
        return "admin/add-perfume";
    }

    @PostMapping("/add/perfume")
    public String addPerfume(@Valid PerfumeRequest perfume, BindingResult bindingResult, Model model,
                             @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("perfume", perfume);
            return "admin/add-perfume";
        }
        perfumeService.savePerfume(perfume, file);
        return "admin/add-perfume";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("roles", Role.values());
        return "admin/edit-user";
    }

    // TODO delete
    @PostMapping("/edit/user")
    public String editUser(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user) {
        userService.userSave(username, form, user);
        return "redirect:/user";
    }
}
