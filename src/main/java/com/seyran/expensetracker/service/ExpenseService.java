package com.seyran.expensetracker.service;

import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;

import java.util.List;

public interface ExpenseService {
    Expense save(Expense expense);
    Expense update(Expense expense);
    Expense findById(Long id);
    List<Expense> findAll();
    void deleteById(Long id);
    List<Expense> getExpenseByUser(User user);
}
