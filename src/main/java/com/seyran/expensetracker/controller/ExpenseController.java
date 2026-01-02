package com.seyran.expensetracker.controller;


import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.service.ExpenseService;
import com.seyran.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @PostMapping
    public Expense createExpense( @RequestBody Expense expense,@RequestParam Long userId) {
        User user =userService.getUserById(userId);
        expense.setUser(user);
        return expenseService.save(expense);
    }
    @GetMapping("/id")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.findById(id);
    }
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }
    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return expenseService.getExpenseByUser(user);
    }
    @PutMapping("/{Id}")
    public Expense updateExpense(@PathVariable Long Id, @RequestBody Expense expense) {
        expense.setId(Id);
        return expenseService.update(expense);
    }
    @DeleteMapping("/{Id}")
    public void deleteExpense(@PathVariable Long Id) {
        expenseService.deleteById(Id);
    }




}
