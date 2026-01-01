package com.seyran.expensetracker.service;

import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Override
    public Expense save(Expense expense) {
        if(expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        return expenseRepository.save(expense);
    }

    @Override
    public Expense update(Expense expense) {
        Expense existing=expenseRepository.findById(expense.getId()).orElseThrow(()->new RuntimeException("Expense with id "+expense.getId()+" not found"));
        existing.setAmount(expense.getAmount());
        existing.setDate(expense.getDate());
        existing.setDescription(expense.getDescription());
        existing.setCategory(expense.getCategory());
        return expenseRepository.save(existing);

    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense with id " + id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense with id " + id + "not found");
        }
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense>findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpenseByUser(User user) {
        return expenseRepository.findByUser(user);
    }

}
