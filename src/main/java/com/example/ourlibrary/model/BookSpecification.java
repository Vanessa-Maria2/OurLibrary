package com.example.ourlibrary.model;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> bookTitle(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), title);
    }

    public static Specification<Book> bookAuthor(String author) {
        return (root, query, cb) -> cb.like(root.get("author"), author);
    }

    public static Specification<Book> bookYear(int year) {
        return (root, query, cb) -> cb.equal(root.get("year"), year);
    }

    public static Specification<Book> bookCategory(String category) {
        return (root, query, cb) -> cb.equal(root.get("category"), category);
    }

    public static Specification<Book> bookDescription(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), description);
    }

}
