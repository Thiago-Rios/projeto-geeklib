package com.infnet.genre_service.service;

import com.infnet.genre_service.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Integer id);
    void save(Genre genre);
    void deleteById(Integer id);
    List<Genre> findAll();
}
