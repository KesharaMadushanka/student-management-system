package com.example.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @RequestMapping("/registration")
    public String registrationForm() {
        return "registration";
    }
}
