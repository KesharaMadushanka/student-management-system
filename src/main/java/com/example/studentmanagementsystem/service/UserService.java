package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.dto.UserDtoUpdate;
import com.example.studentmanagementsystem.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail (String email);
    List<User> getAllUserList();
    boolean deleteUser(Long id);
    Optional<User> findUsingId(Long id);
    boolean emailExists(String email, Long id);
    void updateUser(UserDtoUpdate userDto) throws ChangeSetPersister.NotFoundException;
    boolean isEmailUnique(String email, Long id);
    void updateUser(UserDto userDto);
}
