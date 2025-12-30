package com.seyran.expensetracker.service;

import com.seyran.expensetracker.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
