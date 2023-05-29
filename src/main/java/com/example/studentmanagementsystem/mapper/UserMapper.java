package com.example.studentmanagementsystem.mapper;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.dto.UserDtoUpdate;
import com.example.studentmanagementsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDtoUpdate toDto(User user) {
        UserDtoUpdate userDtoUpdate = new UserDtoUpdate();
        userDtoUpdate.setId(user.getId());
        userDtoUpdate.setEmail(user.getEmail());
        userDtoUpdate.setName(user.getName());
        return userDtoUpdate;
    }

    public User toEntity(UserDtoUpdate userDtoUpdate) {
        User user = new User();
        user.setId(userDtoUpdate.getId());
        user.setEmail(userDtoUpdate.getEmail());
        return user;
    }
}
