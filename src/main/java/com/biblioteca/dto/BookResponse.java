package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private String category;
}