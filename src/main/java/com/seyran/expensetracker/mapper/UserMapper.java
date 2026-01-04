package com.seyran.expensetracker.mapper;

import com.seyran.expensetracker.dto.request.response.UserCreateRequest;
import com.seyran.expensetracker.dto.request.response.UserResponce;
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
    public static UserResponce toResponse(User user) {
        UserResponce responce = new UserResponce();
        responce.setId(user.getId());
        responce.setEmail(user.getEmail());
        responce.setCreatedAt(user.getCreatedAt());
        return responce;
    }
}