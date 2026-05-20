package com.biblioteca.service.impl;

import com.biblioteca.dto.LoanRequest;
import com.biblioteca.dto.LoanResponse;
import com.biblioteca.model.Copy;
import com.biblioteca.model.Loan;
import com.biblioteca.repository.CopyRepository;
import com.biblioteca.repository.LoanRepository;
import com.biblioteca.repository.UserRepository;
import com.biblioteca.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CopyRepository copyRepository;
    private final UserRepository userRepository;

    public LoanServiceImpl(LoanRepository loanRepository, CopyRepository copyRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.copyRepository = copyRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public LoanResponse createLoan(LoanRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new RuntimeException("Usuario no encontrado con id: " + request.getUserId());
        }

        Copy copy = copyRepository.findById(request.getCopyId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + request.getCopyId()));

        if (!"DISPONIBLE".equals(copy.getStatus())) {
            throw new RuntimeException("El ejemplar no está disponible para préstamo");
        }

        Loan loan = new Loan();
        loan.setUserId(request.getUserId());
        loan.setCopyId(request.getCopyId());
        loan.setLoanDate(LocalDate.now());
        loan.setExpectedReturnDate(request.getExpectedReturnDate());
        loan.setStatus("ACTIVO");

        Loan savedLoan = loanRepository.save(loan);

        copy.setStatus("PRESTADO");
        copyRepository.save(copy);

        return mapToResponse(savedLoan);
    }

    @Override
    @Transactional
    public LoanResponse returnLoan(String id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con id: " + id));

        if (!"ACTIVO".equals(loan.getStatus())) {
            throw new RuntimeException("El préstamo ya no está activo");
        }

        loan.setStatus("DEVUELTO");
        Loan updatedLoan = loanRepository.save(loan);

        Copy copy = copyRepository.findById(loan.getCopyId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + loan.getCopyId()));

        copy.setStatus("DISPONIBLE");
        copyRepository.save(copy);

        return mapToResponse(updatedLoan);
    }

    @Override
    public LoanResponse getLoan(String id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con id: " + id));

        return mapToResponse(loan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanResponse> getLoansByUserId(String userId) {
        return loanRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LoanResponse mapToResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getUserId(),
                loan.getCopyId(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate(),
                loan.getStatus()
        );
    }
}