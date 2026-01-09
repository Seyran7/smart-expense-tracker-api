package com.seyran.expensetracker.service;

import com.seyran.expensetracker.dto.request.response.UserUpdateRequest;
import com.seyran.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    User update (Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    Page<User> getAllUsers(Pageable pageable);
}
