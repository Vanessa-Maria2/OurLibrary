package com.example.ourlibrary.dto;

import com.example.ourlibrary.model.Book;
import com.example.ourlibrary.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoanListDTO {

    private User user;

    private List<Book> books;
}
