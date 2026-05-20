package com.biblioteca.controller;

import com.biblioteca.dto.CopyRequest;
import com.biblioteca.dto.CopyResponse;
import com.biblioteca.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
public class CopyController {

    private final CopyService copyService;

    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @PostMapping
    public ResponseEntity<CopyResponse> createCopy(@RequestBody CopyRequest request) {
        CopyResponse response = copyService.createCopy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CopyResponse>> getAllCopies() {
        List<CopyResponse> copies = copyService.getAllCopies();
        return ResponseEntity.ok(copies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CopyResponse> getCopy(@PathVariable String id) {
        CopyResponse response = copyService.getCopy(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CopyResponse> updateCopy(
            @PathVariable String id,
            @RequestBody CopyRequest request) {
        CopyResponse response = copyService.updateCopy(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCopy(@PathVariable String id) {
        copyService.deleteCopy(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/libro/{bookId}")
    public ResponseEntity<List<CopyResponse>> getCopiesByBookId(@PathVariable String bookId) {
        List<CopyResponse> copies = copyService.getCopiesByBookId(bookId);
        return ResponseEntity.ok(copies);
    }
}