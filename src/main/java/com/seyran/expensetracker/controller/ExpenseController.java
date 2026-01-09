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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseCreateRequest request) {
        User user =userService.getUserById(request.getUserId());
       Expense expense= ExpenseMapper.mapToEntity(request,user);
       Expense savedExpense = expenseService.save(expense);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ExpenseMapper.mapToResponse(savedExpense));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id) {
        Expense expense=expenseService.findById(id);
        return ResponseEntity.ok(ExpenseMapper.mapToResponse(expense));
    }
    @GetMapping
    public ResponseEntity<Page<ExpenseResponse>>getAllExpenses(Pageable pageable) {
        Page<Expense> expenses=expenseService.findAll(pageable);

        return ResponseEntity.ok(expenses.map(ExpenseMapper::mapToResponse));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<ExpenseResponse> expenses =expenseService.getExpenseByUser(user).stream().map(ExpenseMapper::mapToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(expenses);
    }
    @PutMapping("/{Id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long Id, @RequestBody ExpenseUpdateRequest request) {
        Expense updatedExpense = expenseService.update(Id,request);
        return ResponseEntity.ok(ExpenseMapper.mapToResponse(updatedExpense));
    }
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long Id) {
        expenseService.deleteById(Id);
        return ResponseEntity.noContent().build();
    }
}
