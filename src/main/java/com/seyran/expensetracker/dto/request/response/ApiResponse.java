package com.seyran.expensetracker.dto.request.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean success;
    private T data;
}
