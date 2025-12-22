package com.seyran.expensetracker.repository;


import com.seyran.expensetracker.model.Expense;
import com.seyran.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByUser( User user);
}
