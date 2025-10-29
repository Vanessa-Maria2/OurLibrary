package com.example.ourlibrary.controller;

import com.example.ourlibrary.dto.LoanListDTO;
import com.example.ourlibrary.dto.LoanRespondeDTO;
import com.example.ourlibrary.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanRespondeDTO> create(@RequestBody LoanListDTO loanDTO) {
        var resp = this.loanService.createLoan(loanDTO);
        return ResponseEntity.ok(resp);
    }
}
