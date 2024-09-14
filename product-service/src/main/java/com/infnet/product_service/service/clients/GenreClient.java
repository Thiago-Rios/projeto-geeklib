package com.infnet.product_service.service.clients;

import com.infnet.product_service.model.Genre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "genre-service", url = "http://localhost:9999")
public interface GenreClient {
    @GetMapping("/genre/{id}")
    Genre getGenre(@PathVariable("id") Integer genreId);
}
