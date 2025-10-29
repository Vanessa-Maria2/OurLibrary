package com.example.ourlibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String biography;

    private String nationality;
}
