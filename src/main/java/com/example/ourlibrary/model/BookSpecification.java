package com.example.ourlibrary.model;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> bookTitle(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), title);
    }

    public static Specification<Book> bookAuthor(String author) {
        return (root, query, cb) -> {
            Join<Book, Author> authors = root.join("authors");
            Expression<String> fullName = cb.concat(cb.concat(authors.get("firstName"), " "), authors.get("lastName"));
            return cb.like(cb.lower(fullName), "%" + author.toLowerCase() + "%");
        };
    }

    public static Specification<Book> bookYear(Integer year) {
        return (root, query, cb) -> cb.equal(root.get("publicationYear"), year);
    }

    public static Specification<Book> bookCategory(String category) {
        return (root, query, cb) -> {
            Join<Book, Category> join = root.join("categories");
            return cb.equal(join.get("name"), category);
        };
    }

    public static Specification<Book> bookDescription(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), description);
    }

}
