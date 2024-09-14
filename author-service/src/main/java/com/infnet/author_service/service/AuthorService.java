package com.infnet.author_service.service;

import com.infnet.author_service.exception.ResourceNotFoundException;
import com.infnet.author_service.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Integer id);
    void save(Author author);
    List<Author> findAll();
    Author update(Integer id, Author author) throws ResourceNotFoundException;
    void deleteById(Integer id) throws ResourceNotFoundException;
}

