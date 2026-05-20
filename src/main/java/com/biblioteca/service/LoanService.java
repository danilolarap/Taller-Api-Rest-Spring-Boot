package com.biblioteca.service;

import com.biblioteca.dto.LoanRequest;
import com.biblioteca.dto.LoanResponse;

import java.util.List;

public interface LoanService {

    LoanResponse createLoan(LoanRequest request);

    LoanResponse returnLoan(String id);

    LoanResponse getLoan(String id);

    List<LoanResponse> getAllLoans();
    
    List<LoanResponse> getLoansByUserId(String userId);
}