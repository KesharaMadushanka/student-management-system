package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail (String email);
    List<User> getAllUserList();
    boolean deleteUser(Long id);
    Optional<User> findUsingId(Long id);
}
