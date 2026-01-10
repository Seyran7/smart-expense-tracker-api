package com.seyran.expensetracker.controller;

import com.seyran.expensetracker.dto.request.response.ApiResponse;
import com.seyran.expensetracker.dto.request.response.UserCreateRequest;
import com.seyran.expensetracker.dto.request.response.UserResponse;
import com.seyran.expensetracker.dto.request.response.UserUpdateRequest;
import com.seyran.expensetracker.mapper.UserMapper;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user= UserMapper.toEntity(request);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,UserMapper.toResponse(savedUser)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>>getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true,UserMapper.toResponse(user)));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getAllUsers(Pageable pageable) {
        Page<UserResponse> users = userService.getAllUsers(pageable)
                .map(UserMapper::toResponse);
        return ResponseEntity.ok(new ApiResponse<>(true,users));
    }
    @GetMapping("/by-email")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(new ApiResponse<>(true,UserMapper.toResponse(user)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id,@Valid @RequestBody UserUpdateRequest request) {

       User userUpdated = userService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true,UserMapper.toResponse(userUpdated)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
