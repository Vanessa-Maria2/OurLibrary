package com.example.ourlibrary.service;

import com.example.ourlibrary.dto.BookDTO;
import com.example.ourlibrary.dto.BookFilterDTO;
import com.example.ourlibrary.model.Author;
import com.example.ourlibrary.model.Category;
import com.example.ourlibrary.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryRepository categoryRepository;

    private BookDTO book, bookDTO2;

    private List<Category> categories;

    @BeforeAll
    void setup() {
        BookDTO bookDTO = BookDTO.builder()
                .title("Book Title")
                .description("Book Description")
                .publicationYear(1990)
                .build();

        book = bookService.create(bookDTO);

        Category category = new Category();
        category.setName("Fiction");
        categoryRepository.save(category);
        categories = categoryRepository.findAll();
    }

    @Test
    void getById() {
        BookDTO bookDTO = BookDTO.builder()
                .title("Book Title")
                .description("Book Description")
                .publicationYear(1990)
                .build();

        var book = bookService.create(bookDTO);
        var bookFind = bookService.getById(book.getId().toString());
        assertNotNull(bookFind);
        assertEquals(book.getTitle(), bookFind.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = {"title", "author", "year", "category", "description"})
    void getAll(String option) {
        BookFilterDTO filter = new BookFilterDTO();
        switch (option) {
            case "title": filter.setTitle(book.getTitle()); break;
            case "author": filter.setAuthor("John Doe"); break;
            case "year": filter.setPublicationYear(1990); break;
            case "category": filter.setCategory(categories.getFirst().getName()); break;
            case "description": filter.setDescription("Book Description"); break;
        }
        filter.setPageable(PageRequest.of(0, 10));
        var resp = bookService.getAll(filter);
        assertNotNull(resp);
        assertEquals(1, resp.getTotalElements());
    }

    @Test
    void create() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        authorService.save(author);

         bookDTO2 = BookDTO.builder()
                .title("Book Title 2")
                .description("Book Description 2")
                .publicationYear(200)
                 .authors(List.of(author))
                 .categories(List.of(categories.get(0)))
                .build();

        var book = bookService.create(bookDTO2);
        var bookFind = bookService.getById(book.getId().toString());
        assertNotNull(bookFind);
        assertEquals(book.getTitle(), bookFind.getTitle());
    }

    @Test
    void deleteById() {
        bookService.deleteById(book.getId().toString());
        assertTrue(true);
    }
}