package com.example.demo.Repository;

import com.example.demo.Model.ModelExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <ModelExpense, Long> {
}
