package com.example.ourlibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class BookFilterDTO {

    @JsonIgnore
    private PageRequest pageable;

    private int page;

    private int size;

    private String title;

    private String description;

    private int publicationYear;

    private String category;

    private String author;
}
