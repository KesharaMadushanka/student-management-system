package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid
            @ModelAttribute("user") UserDto userdto,
            BindingResult result,
            Model model
    ) {
        User existingUser = userService.findUserByEmail(userdto.getEmail());

        // check if user is already registered
        if (existingUser != null) {
            result.rejectValue("email", null,"User already registered!");
        }

        // check if any errors
        if(result.hasErrors()){
            model.addAttribute("user", userdto);
            return "/registration";
        }

        //save user if no errors
        userService.saveUser(userdto);
        return "redirect:/registration?success";
    }
}
