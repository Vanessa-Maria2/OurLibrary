package com.example.ourlibrary.dto;

import com.example.ourlibrary.model.Author;
import com.example.ourlibrary.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BookDTO {

    private UUID id;

    private String title;

    private String description;

    private int publicationYear;

    private String image;

    private List<Category> categories;

    private List<Author> authors;
}
