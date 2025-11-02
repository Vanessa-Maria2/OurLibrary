package com.example.ourlibrary.controller;

import com.example.ourlibrary.dto.LoanDTO;
import com.example.ourlibrary.dto.LoanFilterDTO;
import com.example.ourlibrary.dto.LoanListDTO;
import com.example.ourlibrary.dto.LoanRespondeDTO;
import com.example.ourlibrary.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable String id) {
        var loan = loanService.getLoanById(UUID.fromString(id));
        return ResponseEntity.ok(loan);
    }

    @PostMapping("filtered")
    public ResponseEntity<Page<LoanDTO>> filterLoans(@RequestBody LoanFilterDTO filter) {
        filter.setPageable(PageRequest.of(filter.getPage(), filter.getSize()));
        var result = this.loanService.getFiltered(filter);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<LoanRespondeDTO> create(@RequestBody LoanListDTO loanDTO) {
        var resp = this.loanService.createLoan(loanDTO);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.loanService.deleteLoan(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
