package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loans")
public class Loan {

    @Id
    private String id;

    private String userId;
    private String copyId;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private String status; // ACTIVO, DEVUELTO, VENCIDO
}