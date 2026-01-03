package com.seyran.expensetracker.dto.request.response;

import com.seyran.expensetracker.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String description;
    private Category category;
    private LocalDate date;
    private Long userId;
}
