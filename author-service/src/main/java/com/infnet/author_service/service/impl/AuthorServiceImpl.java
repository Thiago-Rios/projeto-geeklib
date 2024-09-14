package com.infnet.author_service.service.impl;

import com.infnet.author_service.exception.ResourceNotFoundException;
import com.infnet.author_service.model.Author;
import com.infnet.author_service.repository.AuthorRepository;
import com.infnet.author_service.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author update(Integer id, Author author) throws ResourceNotFoundException {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            author.setId(id);
            return authorRepository.save(author);
        } else {
            throw new ResourceNotFoundException("Autor não encontrado com ID: " + id);
        }
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Autor não encontrado com ID: " + id);
        }
    }
}
