package com.example.ourlibrary.dto;

import com.example.ourlibrary.model.Book;

public class BookParse {

    public static Book builderToBook(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .image(bookDTO.getImage())
                .publicationYear(bookDTO.getPublicationYear())
                .authors(bookDTO.getAuthors())
                .categories(bookDTO.getCategories())
                .build();
    }

    public static BookDTO builderToBookDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .image(book.getImage())
                .publicationYear(book.getPublicationYear())
                .authors(book.getAuthors())
                .categories(book.getCategories())
                .build();
    }
}
