package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {

    private String id;
    private String userId;
    private String copyId;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private String status;
}