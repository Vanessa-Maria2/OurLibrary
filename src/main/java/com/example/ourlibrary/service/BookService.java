package com.example.ourlibrary.service;

import com.example.ourlibrary.dto.BookDTO;
import com.example.ourlibrary.dto.BookFilterDTO;
import com.example.ourlibrary.dto.BookParse;
import com.example.ourlibrary.model.Book;
import com.example.ourlibrary.model.BookSpecification;
import com.example.ourlibrary.repository.BookRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO getById(String id) {
        Book book = this.bookRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return BookParse.builderToBookDTO(book);
    }

    public Page<BookDTO> getAll(BookFilterDTO filter) {
        Specification<Book> specification = Specification.<Book>allOf()
                        .and(StringUtils.isBlank(filter.getTitle()) ? null : BookSpecification.bookTitle(filter.getTitle()))
                .and(StringUtils.isBlank(filter.getDescription()) ? null : BookSpecification.bookDescription(filter.getDescription()))
                .and(filter.getPublicationYear() == null ? null : BookSpecification.bookYear(filter.getPublicationYear()))
                .and(StringUtils.isBlank(filter.getAuthor()) ? null : BookSpecification.bookAuthor(filter.getAuthor()))
                .and(StringUtils.isBlank(filter.getCategory()) ? null : BookSpecification.bookCategory(filter.getCategory()));

        var all = this.bookRepository.findAll(specification, filter.getPageable().withSort(Sort.by(Sort.Direction.DESC, "id")));
        var allDTO = all.stream().map(BookParse::builderToBookDTO).toList();
        return new PageImpl<>(allDTO, all.getPageable(), all.getTotalElements());
    }

    public BookDTO create(BookDTO bookDTO) {
        var book = BookParse.builderToBook(bookDTO);
        var saved = this.bookRepository.save(book);
        return BookParse.builderToBookDTO(saved);
    }

    public void deleteById(String id) {
        this.bookRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        this.bookRepository.deleteById(UUID.fromString(id));
    }
}
