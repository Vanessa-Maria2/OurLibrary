package com.example.ourlibrary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoanRespondeDTO {

    private List<LoanDTO> loanDTOS;
}
