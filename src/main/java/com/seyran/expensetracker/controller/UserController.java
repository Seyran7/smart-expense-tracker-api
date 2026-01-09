package com.seyran.expensetracker.controller;

import com.seyran.expensetracker.dto.request.response.UserCreateRequest;
import com.seyran.expensetracker.dto.request.response.UserResponse;
import com.seyran.expensetracker.dto.request.response.UserUpdateRequest;
import com.seyran.expensetracker.mapper.UserMapper;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user= UserMapper.toEntity(request);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(savedUser));
    }
    @GetMapping("/{Id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(Pageable pageable) {
        List<UserResponse> users = userService.getAllUsers(pageable)
                .stream().map(UserMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
    @GetMapping
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {

       User userUpdated = userService.update(id, request);
        return ResponseEntity.ok(UserMapper.toResponse(userUpdated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
