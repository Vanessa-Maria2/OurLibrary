package com.example.ourlibrary.repository;

import com.example.ourlibrary.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
