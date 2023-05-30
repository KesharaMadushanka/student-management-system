package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.UserDtoUpdate;
import com.example.studentmanagementsystem.mapper.UserMapper;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/ViewUsers")
public class ViewUsersController {

    private final UserService userService;
    private final UserMapper userMapper;

    public ViewUsersController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    //User CRUD controller
    @GetMapping()
    public String viewUsers(Model model) {
        List<User> userList = userService.getAllUserList();
        model.addAttribute("users", userList);
        return "admin/viewUsers";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {

        if (userService.deleteUser(id)) {
            return "redirect:/admin/ViewUsers?delSuccess";
        } else {
            return "redirect:/admin/ViewUsers?delError";
        }
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOptional = userService.findUsingId(id);
        if (userOptional.isEmpty()) {
            // Handle user not found case (e.g., show an error message or redirect to a user list)

            return "redirect:/admin/ViewUsers";
        }

        User user = userOptional.get();
        UserDtoUpdate userDtoUpdate = userMapper.toDto(user);
        System.out.println(userDtoUpdate.getName());

        // Add the user ID and user details to the model
        model.addAttribute("id", id);
        model.addAttribute("userDetails", userDtoUpdate);

        return "admin/editUserForm";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("userDetails") UserDtoUpdate userDtoUpdate, BindingResult result, Model model) throws ChangeSetPersister.NotFoundException {

        boolean emailExists = false;
// Check if the user ID exists and retrieve the existing user
        Optional<User> existingUserOptional = userService.findUsingId(id);
        if (existingUserOptional.isEmpty()) {
            // Handle user not found case (e.g., show an error message or redirect to a user list)
            System.out.println("error2");
            return "redirect:/admin/ViewUsers";
        }

        User existingUser = existingUserOptional.get();

        // Validate the email for uniqueness, excluding the current user ID
        if (!userService.isEmailUnique(userDtoUpdate.getEmail(), existingUser.getId())) {
            result.rejectValue("email", null, "Email is already taken");
            emailExists = true;
        }

        if (result.hasErrors()) {

            boolean emailErrors = result.hasFieldErrors("email");
            boolean nameErrors = result.hasFieldErrors("name");

            model.addAttribute("id", id);

            if (emailExists) {
                return String.format("redirect:/admin/ViewUsers/edit/%d?emailExists", id);
            } else if (emailErrors) {
                return String.format("redirect:/admin/ViewUsers/edit/%d?emailError", id);
            } else if (nameErrors) {
                return String.format("redirect:/admin/ViewUsers/edit/%d?nameError", id);
            } else {
                return String.format("redirect:/admin/ViewUsers/edit/%d?Error", id);
            }
        }

        // Update the user details
        userService.updateUser(userDtoUpdate);
        // Redirect to the user list or show success message
        return "redirect:/admin/ViewUsers?editSuccess";
    }
}

