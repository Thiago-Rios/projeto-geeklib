package com.infnet.author_service;

import com.infnet.author_service.model.Author;
import com.infnet.author_service.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    AuthorService authorService;

    @Test
    @DisplayName("Deve autores pelo ID")
    public void testaInsert() {
        List<Author> all = authorService.findAll();
        int initState = all.size();
        Author author = new Author();
        author.setName("Teste");
        authorService.save(author);
        all = authorService.findAll();
        int finalState =all.size();
        assertEquals(initState + 1, finalState);
        authorService.deleteById(author.getId());
    }

    @Test
    @DisplayName("Deve autores pelo ID")
    public void testaFindById() {
        Author genre = new Author();
        genre.setName("Teste");
        authorService.save(genre);
        List<Author> all = authorService.findAll();
        Author genre2 = all.getFirst();
        Optional<Author> byId = authorService.findById(genre2.getId());
        assertTrue(byId.isPresent());
        Optional<Author> noProduct = authorService.findById(-1);
        assertTrue(noProduct.isEmpty());
        authorService.deleteById(genre.getId());
    }

    @Test
    @DisplayName("Deve retornar autores")
    public void testaFindAll() {
        Author genre = new Author();
        genre.setName("Suspense");
        authorService.save(genre);
        Author genre2 = new Author();
        genre2.setName("Aventura");
        authorService.save(genre2);
        List<Author> all = authorService.findAll();
        int finalState = all.size();
        assertEquals(6, finalState);
    }
}
