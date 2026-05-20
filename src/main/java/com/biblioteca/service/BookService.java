package com.biblioteca.service;

import com.biblioteca.dto.BookRequest;
import com.biblioteca.dto.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest request);

    BookResponse updateBook(String id, BookRequest request);

    void deleteBook(String id);

    BookResponse getBook(String id);

    List<BookResponse> getAllBooks();
}