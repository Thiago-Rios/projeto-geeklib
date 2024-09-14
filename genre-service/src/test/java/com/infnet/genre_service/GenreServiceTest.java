package com.infnet.genre_service;

import com.infnet.genre_service.model.Genre;
import com.infnet.genre_service.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GenreServiceTest {
    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("Deve generos pelo ID")
    public void testaInsert() {
        List<Genre> all = genreService.findAll();
        int initState = all.size();
        Genre genre = new Genre();
        genre.setName("Teste");
        genreService.save(genre);
        all =genreService.findAll();
        int finalState =all.size();
        assertEquals(initState + 1, finalState);
        genreService.deleteById(genre.getId());
    }

    @Test
    @DisplayName("Deve generos pelo ID")
    public void testaFindById() {
        Genre genre = new Genre();
        genre.setName("Teste");
        genreService.save(genre);
        List<Genre> all = genreService.findAll();
        Genre genre2 = all.getFirst();
        Optional<Genre> byId = genreService.findById(genre2.getId());
        assertTrue(byId.isPresent());
        Optional<Genre> noProduct = genreService.findById(-1);
        assertTrue(noProduct.isEmpty());
        genreService.deleteById(genre.getId());
    }

    @Test
    @DisplayName("Deve retornar generos")
    public void testaFindAll() {
        Genre genre = new Genre();
        genre.setName("Suspense");
        genreService.save(genre);
        Genre genre2 = new Genre();
        genre2.setName("Aventura");
        genreService.save(genre2);
        List<Genre> all = genreService.findAll();
        int finalState = all.size();
        assertEquals(5, finalState);
    }

}
