package com.seyran.expensetracker.controller;


import com.seyran.expensetracker.dto.request.response.ExpenseCreateRequest;
import com.seyran.expensetracker.dto.request.response.ExpenseResponse;
import com.seyran.expensetracker.dto.request.response.ExpenseUpdateRequest;
import com.seyran.expensetracker.mapper.ExpenseMapper;
import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.service.ExpenseService;
import com.seyran.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @PostMapping
    public ExpenseResponse createExpense(@Valid @RequestBody ExpenseCreateRequest request) {
        User user =userService.getUserById(request.getUserId());
       Expense expense= ExpenseMapper.mapToEntity(request,user);
       Expense savedExpense = expenseService.save(expense);
        return ExpenseMapper.mapToResponse(savedExpense);
    }
    @GetMapping("/id")
    public ExpenseResponse getExpenseById(@PathVariable Long id) {
        Expense expense=expenseService.findById(id);
        return ExpenseMapper.mapToResponse(expense);
    }
    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {
        return expenseService.findAll().stream().map(ExpenseMapper::mapToResponse).collect(Collectors.toList());
    }
    @GetMapping("/user/{userId}")
    public List<ExpenseResponse> getExpensesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return expenseService.getExpenseByUser(user).stream().map(ExpenseMapper::mapToResponse).collect(Collectors.toList());
    }
    @PutMapping("/{Id}")
    public ExpenseResponse updateExpense(@PathVariable Long Id, @RequestBody ExpenseUpdateRequest request) {
        Expense updatedExpense = expenseService.update(Id,request);
        return ExpenseMapper.mapToResponse(updatedExpense);
    }
    @DeleteMapping("/{Id}")
    public void deleteExpense(@PathVariable Long Id) {
        expenseService.deleteById(Id);
    }
}
