package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.UserDto;
import com.example.studentmanagementsystem.dto.UserDtoUpdate;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.RoleRepository;
import com.example.studentmanagementsystem.repository.UserRepository;
import com.example.studentmanagementsystem.util.TableConstants;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<User> getAllUserList() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<User> findUsingId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean emailExists(String email, Long id) {
        User user = userRepository.findByEmailAndIdNot(email, id);
        return user != null;
    }

    @Override
    public void updateUser(UserDtoUpdate userDto) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Update the user details
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());


        userRepository.save(user);
    }

    @Override
    public boolean isEmailUnique(String email, Long id) {
        User user = userRepository.findByEmail(email);
        return user == null || user.getId().equals(id);
    }

    @Override
    public void updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(userDto.getName());
            existingUser.setEmail(userDto.getEmail());
            // Exclude updating the password field
            // existingUser.setPassword(userDto.getPassword());
            userRepository.save(existingUser);
        }
    }
}

