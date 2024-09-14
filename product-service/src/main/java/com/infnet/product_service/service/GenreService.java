package com.infnet.product_service.service;

import com.infnet.product_service.model.Genre;
import com.infnet.product_service.service.clients.GenreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreClient genreClient;

    public Genre getGenreById(Integer genreId) {
        return genreClient.getGenre(genreId);
    }
}
