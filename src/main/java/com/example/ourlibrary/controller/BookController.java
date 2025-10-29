package com.example.ourlibrary.controller;

import com.example.ourlibrary.dto.BookDTO;
import com.example.ourlibrary.dto.BookFilterDTO;
import com.example.ourlibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        var result = this.bookService.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("filtered")
    public ResponseEntity<Page<BookDTO>> filterBook(@RequestBody BookFilterDTO filter) {
        filter.setPageable(PageRequest.of(filter.getPage(), filter.getSize()));
        var resp = this.bookService.getAll(filter);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        var saved = this.bookService.create(bookDTO);
        return ResponseEntity.ok().body(saved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
