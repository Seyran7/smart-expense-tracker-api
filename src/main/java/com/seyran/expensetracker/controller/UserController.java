package com.seyran.expensetracker.controller;

import com.seyran.expensetracker.dto.request.response.UserCreateRequest;
import com.seyran.expensetracker.dto.request.response.UserResponse;
import com.seyran.expensetracker.dto.request.response.UserUpdateRequest;
import com.seyran.expensetracker.mapper.UserMapper;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserCreateRequest request) {
        User user= UserMapper.toEntity(request);
        User savedUser = userService.saveUser(user);
        return UserMapper.toResponse(savedUser);
    }
    @GetMapping("/{Id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return UserMapper.toResponse(userService.getUserById(id));
    }
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }
    @GetMapping
    public UserResponse getUserByEmail(@RequestParam String email) {
        return UserMapper.toResponse(userService.getUserByEmail(email));
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {

       User userUpdated = userService.update(id, request);
        return UserMapper.toResponse(userUpdated);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
