package com.infnet.product_service.service;

import com.infnet.product_service.model.Author;
import com.infnet.product_service.service.clients.AuthorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorClient authorClient;

    public Author getAuthorById(Integer authorId) {
        return authorClient.getAuthor(authorId);
    }
}
