package org.example.service.impl;


import org.example.model.User;
import org.example.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserById(int userId) {
        return users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void updateUser(User user) {
        deleteUser(user.getUserId());
        addUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        users.removeIf(u -> u.getUserId() == userId);
    }
}
