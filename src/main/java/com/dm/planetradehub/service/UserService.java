package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User getUserByEmail(String email);

    User signUpUser(User user);

    User saveUser(User user);
    public User updateUser(User user, Authentication authentication);

    void deleteUserById(Long id);
    void deleteUserByEmail(String email);
}
