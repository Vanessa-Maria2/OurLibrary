package com.example.ourlibrary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

@Data
@Builder
public class LoanFilterDTO {

    @JsonIgnore
    private PageRequest pageable;

    private int page;

    private int size;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    private UserRequestLoanDTO user;

    private BookRequestLoanDTO book;
}
