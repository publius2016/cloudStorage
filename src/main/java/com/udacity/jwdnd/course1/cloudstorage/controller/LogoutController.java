package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logoutSuccess")
public class LogoutController {

    @PostMapping
    public String logout(Model model) {
        model.addAttribute("logoutSuccess", true);

        return "login";
    }
}
