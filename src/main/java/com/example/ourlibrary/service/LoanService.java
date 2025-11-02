package com.example.ourlibrary.service;

import com.example.ourlibrary.dto.LoanDTO;
import com.example.ourlibrary.dto.LoanListDTO;
import com.example.ourlibrary.dto.LoanParse;
import com.example.ourlibrary.dto.LoanRespondeDTO;
import com.example.ourlibrary.model.Book;
import com.example.ourlibrary.model.Loan;
import com.example.ourlibrary.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return LoanParse.builderToLoan(loan);
    }

    public LoanRespondeDTO createLoan(LoanListDTO loanDTO) {
        List<Loan> loans = new ArrayList<>();
        for (Book book : loanDTO.getBooks()) {
            var newLoan = new Loan();
            newLoan.setBook(book);
            newLoan.setUser(loanDTO.getUser());
            newLoan.setStartDate(LocalDateTime.now());
            newLoan.setEndDate(LocalDateTime.now().plusDays(15));
            loans.add(newLoan);
        }
        var loansSaved = this.loanRepository.saveAll(loans);
        var loansDTO = loansSaved.stream().map(LoanParse::builderToLoan).toList();
        return LoanRespondeDTO.builder().loanDTOS(loansDTO).build();
    }

    public void deleteLoan(Long id) {
        var loan = this.loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        this.loanRepository.delete(loan);
    }
}
