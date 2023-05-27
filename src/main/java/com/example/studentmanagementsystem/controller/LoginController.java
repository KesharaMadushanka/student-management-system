package com.example.studentmanagementsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Controller
public class LoginController {

    @GetMapping("/redirect")
    public String redirect (Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                return "redirect:/admin/home";
            } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                return "redirect:/user/home";
            }
        }

        return "redirect:/login?error";
    }
}
