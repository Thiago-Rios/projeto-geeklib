package com.infnet.product_service.service.impl;

import com.infnet.product_service.filters.ProductFilters;
import com.infnet.product_service.model.Author;
import com.infnet.product_service.model.Genre;
import com.infnet.product_service.model.Product;
import com.infnet.product_service.repository.ProductRepository;
import com.infnet.product_service.service.AuthorService;
import com.infnet.product_service.service.GenreService;
import com.infnet.product_service.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {

        Genre genre = genreService.getGenreById(product.getGenreId());

        Author author = authorService.getAuthorById(product.getAuthorId());

        product.setGenreId(genre.getId());
        product.setGenreName(genre.getName());

        product.setAuthorName(author.getName());
        product.setAuthorId(author.getId());

        log.info("Produto criado com as seguintes informações: {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Integer id, Product product) {
        product.setId(id);

        Genre genre = genreService.getGenreById(product.getGenreId());

        Author author = authorService.getAuthorById(product.getAuthorId());

        product.setGenreId(genre.getId());
        product.setGenreName(genre.getName());

        product.setAuthorName(author.getName());
        product.setAuthorId(author.getId());

        productRepository.save(product);

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findAllByNameContains(String name) {
        return productRepository.findAllByNameContains(name);
    }

    @Override
    public List<Product> findAllFantasia() {
        return productRepository.findAllFantasia();
    }

    @Override
    public List<Product> findWithFilters(ProductFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getName().isPresent()) {
            String query = filters.getName().get() + "%";
            Predicate name = cb.like(productRoot.get("name"), query);
            predicates.add(name);
        }
        if (filters.getGenreName().isPresent()) {
            Predicate genre = cb.equal(productRoot.get("genreName"), filters.getGenreName().get());
            predicates.add(genre);
        }
        if (filters.getAuthorName().isPresent()) {
            Predicate author = cb.equal(productRoot.get("authorName"), filters.getAuthorName().get());
            predicates.add(author);
        }
        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(array);
        return entityManager.createQuery(cq).getResultList();
    }

}

