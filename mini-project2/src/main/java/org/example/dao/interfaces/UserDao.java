package org.example.dao.interfaces;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
}

