package com.seyran.expensetracker.mapper;

import com.seyran.expensetracker.dto.request.response.UserCreateRequest;
import com.seyran.expensetracker.dto.request.response.UserResponse;
import com.seyran.expensetracker.model.User;


import java.time.LocalDateTime;

public class UserMapper {
    public static User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}