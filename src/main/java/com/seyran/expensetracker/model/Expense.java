package com.seyran.expensetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name="expenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;
    private String description;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @ JoinColumn(name="user_id", nullable = false)
    private User user;
}
