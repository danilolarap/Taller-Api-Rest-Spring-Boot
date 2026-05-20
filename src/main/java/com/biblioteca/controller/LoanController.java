package com.biblioteca.controller;

import com.biblioteca.dto.LoanRequest;
import com.biblioteca.dto.LoanResponse;
import com.biblioteca.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody LoanRequest request) {
        LoanResponse response = loanService.createLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/devolucion")
    public ResponseEntity<LoanResponse> returnLoan(@PathVariable("id") String id) {
        LoanResponse response = loanService.returnLoan(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable("id") String id) {
        LoanResponse response = loanService.getLoan(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<LoanResponse>> getLoansByUserId(@PathVariable("userId") String userId) {
        List<LoanResponse> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }
}