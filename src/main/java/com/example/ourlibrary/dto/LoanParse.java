package com.example.ourlibrary.dto;

import com.example.ourlibrary.model.Loan;

public class LoanParse {

    public static LoanDTO builderToLoan(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .book(loan.getBook())
                .user(loan.getUser())
                .build();
    }
}
