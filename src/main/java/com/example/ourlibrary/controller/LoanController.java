package com.example.ourlibrary.controller;

import com.example.ourlibrary.dto.LoanDTO;
import com.example.ourlibrary.dto.LoanListDTO;
import com.example.ourlibrary.dto.LoanRespondeDTO;
import com.example.ourlibrary.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        var loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<LoanRespondeDTO> create(@RequestBody LoanListDTO loanDTO) {
        var resp = this.loanService.createLoan(loanDTO);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
