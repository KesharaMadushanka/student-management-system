package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.model.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail (String email);
}
