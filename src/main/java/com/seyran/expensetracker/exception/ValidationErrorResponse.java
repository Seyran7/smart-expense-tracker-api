package com.seyran.expensetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
    private int status;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}
