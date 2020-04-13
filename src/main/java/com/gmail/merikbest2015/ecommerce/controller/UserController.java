package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.Role;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("add")
    public String addToBD() {
        return "admin/addToDb";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add")
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

        return "admin/addToDb";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.userSave(username, form, user);
        return "redirect:/user";
    }

    @GetMapping("edit")
    public String editProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
//        model.addAttribute("user", user);
        return "userEditProfile";
//        return "profile";
    }

    @PostMapping("edit")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }
}