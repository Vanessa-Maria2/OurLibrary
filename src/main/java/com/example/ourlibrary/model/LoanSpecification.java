package com.example.ourlibrary.model;

import com.example.ourlibrary.dto.BookRequestLoanDTO;
import com.example.ourlibrary.dto.UserRequestLoanDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class LoanSpecification {

    public static Specification<Loan> loanStartDate(LocalDateTime startDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<Loan> loanEndDate(LocalDateTime endDate) {
        return (root, query, cb) ->  cb.greaterThanOrEqualTo(root.get("endDate"), endDate);
    }

    public static Specification<Loan> loanUser(UserRequestLoanDTO user) {
        return (root, query, cb) -> {
            Join<Loan, User> userJoin = root.join("user");
            Predicate predicate = cb.conjunction();

            if (user.getName() != null && !user.getName().isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(userJoin.get("name")), "%" + user.getName().toLowerCase() + "%"));
            }
            if (user.getCpf() != null && !user.getCpf().isBlank()) {
                predicate = cb.and(predicate, cb.equal(userJoin.get("cpf"), user.getCpf()));
            }
            if (user.getEmail() != null && !user.getEmail().isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(userJoin.get("email")), "%" + user.getEmail().toLowerCase() + "%"));
            }
            return predicate;
        };
    }

    public static Specification<Loan> loanBook(BookRequestLoanDTO book) {
        return (root, query, cb) -> {
            Join<Loan, Book> bookJoin = root.join("book");
            Predicate predicate = cb.conjunction();

            if (book.getTitle() != null && !book.getTitle().isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(bookJoin.get("title")), "%" + book.getTitle().toLowerCase() + "%"));
            }

            if (book.getDescription() != null && !book.getDescription().isBlank()) {
                predicate = cb.and(predicate, cb.like(bookJoin.get("description"), "%" + book.getDescription() + "%"));
            }

            return predicate;
        };
    }
}
