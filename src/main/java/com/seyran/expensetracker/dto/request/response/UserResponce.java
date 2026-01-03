package com.seyran.expensetracker.dto.request.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponce {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
}
