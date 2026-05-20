package com.biblioteca.repository;

import com.biblioteca.model.Copy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends MongoRepository<Copy, String> {
    List<Copy> findByBookId(String bookId);
}