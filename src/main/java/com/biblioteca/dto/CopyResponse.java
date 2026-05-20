package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyResponse {

    private String id;
    private String bookId;
    private String copyCode;
    private String status;
    private String location;
}