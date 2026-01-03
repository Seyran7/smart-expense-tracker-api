package com.seyran.expensetracker.dto.request.response;

import com.seyran.expensetracker.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseCreateRequest {
    @NotNull(message="Amount is required")
    @Positive(message="Amount must be positive")
    private BigDecimal amount;

    private String description;

    private LocalDate date;

    @NotNull(message="Category is required")
    private Category category;
    @NotNull(message="User Id is required")
    private Long userId;

}
