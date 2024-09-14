package com.infnet.product_service.service.clients;

import com.infnet.product_service.model.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-service", url = "http://localhost:9999")
public interface AuthorClient {
    @GetMapping("/author/{id}")
    Author getAuthor(@PathVariable Integer id);
}
