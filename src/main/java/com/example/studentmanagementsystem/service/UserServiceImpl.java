package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.RoleRepository;
import com.example.studentmanagementsystem.repository.UserRepository;
import com.example.studentmanagementsystem.util.TableConstants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TableConstants.Roles.USER);

        if (role == null) {
            role = roleRepository.save(new Role(TableConstants.Roles.USER));
        }

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}