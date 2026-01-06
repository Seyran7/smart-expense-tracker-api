package com.seyran.expensetracker.dto.request.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
}
