package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);
}
