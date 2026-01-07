package com.seyran.expensetracker.service;

import com.seyran.expensetracker.dto.request.response.ExpenseUpdateRequest;
import com.seyran.expensetracker.exception.NotFoundException;
import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public Expense update(Long Id, ExpenseUpdateRequest request) {
        Expense existing=expenseRepository.findById(Id).orElseThrow(()->new NotFoundException("Expense with id "+Id+" not found"));
        if(request.getAmount() != null) {
            existing.setAmount(request.getAmount());
        }
        if(request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }
        if(request.getDate() != null) {
            existing.setDate(request.getDate());
        }
        if(request.getCategory() != null) {
            existing.setCategory(request.getCategory());
        }
        return expenseRepository.save(existing);

    }

    @Override
    public Expense findById(Long Id) {
        return expenseRepository.findById(Id).orElseThrow(()->new NotFoundException("Expense with id " + Id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new NotFoundException("Expense with id " + id + "not found");
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
