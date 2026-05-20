package com.biblioteca.service.impl;

import com.biblioteca.dto.CopyRequest;
import com.biblioteca.dto.CopyResponse;
import com.biblioteca.model.Copy;
import com.biblioteca.repository.CopyRepository;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.service.CopyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyServiceImpl implements CopyService {

    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;

    public CopyServiceImpl(CopyRepository copyRepository, BookRepository bookRepository) {
        this.copyRepository = copyRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public CopyResponse createCopy(CopyRequest request) {
        if (!bookRepository.existsById(request.getBookId())) {
            throw new RuntimeException("Libro no encontrado con id: " + request.getBookId());
        }

        Copy copy = new Copy();
        copy.setBookId(request.getBookId());
        copy.setCopyCode(request.getCopyCode());
        copy.setStatus(request.getStatus() != null ? request.getStatus() : "DISPONIBLE");
        copy.setLocation(request.getLocation());

        Copy savedCopy = copyRepository.save(copy);

        return mapToResponse(savedCopy);
    }

    @Override
    public CopyResponse updateCopy(String id, CopyRequest request) {
        Copy copy = copyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + id));

        if (!copy.getBookId().equals(request.getBookId()) && !bookRepository.existsById(request.getBookId())) {
            throw new RuntimeException("Libro no encontrado con id: " + request.getBookId());
        }

        copy.setBookId(request.getBookId());
        copy.setCopyCode(request.getCopyCode());
        copy.setStatus(request.getStatus());
        copy.setLocation(request.getLocation());

        Copy updatedCopy = copyRepository.save(copy);

        return mapToResponse(updatedCopy);
    }

    @Override
    public void deleteCopy(String id) {
        if (!copyRepository.existsById(id)) {
            throw new RuntimeException("Ejemplar no encontrado con id: " + id);
        }

        copyRepository.deleteById(id);
    }

    @Override
    public CopyResponse getCopy(String id) {
        Copy copy = copyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + id));

        return mapToResponse(copy);
    }

    @Override
    public List<CopyResponse> getAllCopies() {
        return copyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CopyResponse> getCopiesByBookId(String bookId) {
        return copyRepository.findByBookId(bookId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CopyResponse mapToResponse(Copy copy) {
        return new CopyResponse(
                copy.getId(),
                copy.getBookId(),
                copy.getCopyCode(),
                copy.getStatus(),
                copy.getLocation()
        );
    }
}