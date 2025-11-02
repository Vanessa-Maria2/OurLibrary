package com.example.ourlibrary.service;

import com.example.ourlibrary.dto.*;
import com.example.ourlibrary.model.Book;
import com.example.ourlibrary.model.Loan;
import com.example.ourlibrary.model.LoanSpecification;
import com.example.ourlibrary.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanDTO getLoanById(UUID id) {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return LoanParse.builderToLoan(loan);
    }

    public Page<LoanDTO> getFiltered(LoanFilterDTO filter) {
        Specification<Loan> specification = Specification.<Loan>allOf()
                .and(filter.getStartDate() == null ? null : LoanSpecification.loanStartDate(filter.getStartDate()))
                .and(filter.getEndDate() == null ? null : LoanSpecification.loanEndDate(filter.getEndDate()))
                .and(filter.getBook() == null ? null : LoanSpecification.loanBook(filter.getBook()))
                .and(filter.getUser() == null ? null : LoanSpecification.loanUser(filter.getUser()));

        var all = loanRepository.findAll(specification, filter.getPageable().withSort(Sort.by(Sort.Direction.DESC, "id")));
        var allDTO = all.stream().map(LoanParse::builderToLoan).toList();
        return new PageImpl<>(allDTO, all.getPageable(), all.getTotalElements());
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

    public void deleteLoan(UUID id) {
        var loan = this.loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        this.loanRepository.delete(loan);
    }
}
