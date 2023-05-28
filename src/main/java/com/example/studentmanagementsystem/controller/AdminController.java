package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "admin/admin";
    }

    //User CRUD controller
    @GetMapping("/ViewUsers")
    public String viewUsers(Model model) {
        List<User> userList = userService.getAllUserList();
        model.addAttribute("users",userList);
        return "admin/viewUsers";
    }

    @PostMapping("/ViewUsers/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {

        if (userService.deleteUser(id)){
            return "redirect:/admin/ViewUsers?delSuccess";
        } else {
            return "redirect:/admin/ViewUsers?delError";
        }
    }

}
