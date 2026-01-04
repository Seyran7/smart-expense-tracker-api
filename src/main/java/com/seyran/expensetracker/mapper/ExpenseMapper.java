package com.seyran.expensetracker.mapper;

import com.seyran.expensetracker.dto.request.response.ExpenseCreateRequest;
import com.seyran.expensetracker.dto.request.response.ExpenseResponse;
import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;

import java.time.LocalDate;

public class ExpenseMapper {
    public static Expense mapToEntity(ExpenseCreateRequest request, User user) {
        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate() != null ? request.getDate() : LocalDate.now());
        expense.setUser(user);
        return expense;
    }
    public static ExpenseResponse mapToResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setAmount(expense.getAmount());
        response.setDescription(expense.getDescription());
        response.setCategory(expense.getCategory());
        response.setDate(expense.getDate());
        response.setId(expense.getId());
        response.setUserId(expense.getUser().getId());
        return response;
    }
}
