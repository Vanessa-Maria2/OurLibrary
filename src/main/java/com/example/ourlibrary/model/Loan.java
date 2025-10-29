package com.example.ourlibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class Loan {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}
