package com.biblioteca.service;

import com.biblioteca.dto.CopyRequest;
import com.biblioteca.dto.CopyResponse;

import java.util.List;

public interface CopyService {

    CopyResponse createCopy(CopyRequest request);

    CopyResponse updateCopy(String id, CopyRequest request);

    void deleteCopy(String id);

    CopyResponse getCopy(String id);

    List<CopyResponse> getAllCopies();
    
    List<CopyResponse> getCopiesByBookId(String bookId);
}