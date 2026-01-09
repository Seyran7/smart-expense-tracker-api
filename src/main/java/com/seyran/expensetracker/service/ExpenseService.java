package com.seyran.expensetracker.service;

import com.seyran.expensetracker.dto.request.response.ExpenseUpdateRequest;
import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ExpenseService {
    Expense save(Expense expense);
    Expense update(Long Id, ExpenseUpdateRequest request);
    Expense findById(Long id);
    Page <Expense> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Expense> getExpenseByUser(User user);

}
