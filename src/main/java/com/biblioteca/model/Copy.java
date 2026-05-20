package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "copies")
public class Copy {

    @Id
    private String id;

    private String bookId;
    private String copyCode;
    private String status; // DISPONIBLE, PRESTADO, MANTENIMIENTO
    private String location;
}