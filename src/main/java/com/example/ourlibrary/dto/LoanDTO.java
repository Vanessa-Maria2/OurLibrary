package com.example.ourlibrary.dto;

import com.example.ourlibrary.model.Book;
import com.example.ourlibrary.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class LoanDTO {

    private UUID id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private User user;

    private Book book;
}
