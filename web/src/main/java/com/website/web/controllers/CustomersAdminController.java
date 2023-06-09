package com.website.web.controllers;

import com.website.web.models.Users;
import com.website.web.services.interfaces.IGetImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CustomersAdminController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGetImageService getImageService;

    @SneakyThrows
    @GetMapping("/users")
    public String Users(Model model, Principal principal) {
        Iterable<Users> users = userService.findAllNoAdmin();
        model.addAttribute("user", users);

        getImageService.image(model, principal);
        return "Users";
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
    @PostMapping("/users/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/users";
    }
}
