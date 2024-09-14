package com.infnet.product_service;

import com.infnet.product_service.filters.ProductFilters;
import com.infnet.product_service.model.Product;
import com.infnet.product_service.service.GenreService;
import com.infnet.product_service.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("Deve inserir um produto no banco")
    public void testaInsert() {
        List<Product> all = productService.getAll();
        int initState = all.size();
        Product product = new Product();
        product.setName("Teste 1");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        all = productService.getAll();
        int finalState = all.size();
        assertEquals(initState + 1, finalState);
        productService.deleteById(product.getId());
    }

    @Test
    @DisplayName("Deve retornar um produto no banco")
    public void testaGetById() {
        Product product = new Product();
        product.setName("Teste 2");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        List<Product> all = productService.getAll();
        Product product2 = all.getFirst();
        Optional<Product> byId = productService.findById(product2.getId());
        assertTrue(byId.isPresent());
        Optional<Product> noProduct = productService.findById(-1);
        assertTrue(noProduct.isEmpty());
        productService.deleteById(product.getId());
    }

    @Test
    @DisplayName("Deve buscar um produto pelo nome")
    public void testaPeloNOme() {
        Product product = new Product();
        product.setName("Eragon");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        Product product2 = new Product();
        product2.setName("Eldest");
        product2.setAuthorId(1);
        product2.setGenreId(1);
        product2.setDescription("Sinopse teste 2");
        productService.save(product2);

        List<Product> result = productService.findAllByName("Eragon");
        assertEquals(1, result.size());
        List<Product> startsWithE = productService.findAllByNameContains("E");
        assertEquals(2, startsWithE.size());
        productService.deleteById(product.getId());
        productService.deleteById(product2.getId());
    }

    @Test
    @DisplayName("Deve buscar os produtos pelo genero Fantasia")
    public void testaPeloGeneroFantasia() {
        Product product = new Product();
        product.setName("Eragon");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        Product product2 = new Product();
        product2.setName("Eldest");
        product2.setAuthorId(1);
        product2.setGenreId(1);
        product2.setDescription("Sinopse teste 2");
        productService.save(product2);

        List<Product> result = productService.findAllFantasia();
        assertEquals(2, result.size());
        productService.deleteById(product.getId());
        productService.deleteById(product2.getId());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando por nome e gênero")
    public void testFindWithFiltersByNameAndGenre() {
        Product product = new Product();
        product.setName("Eragon");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        Product product2 = new Product();
        product2.setName("Eldest");
        product2.setAuthorId(1);
        product2.setGenreId(1);
        product2.setDescription("Sinopse teste 2");
        productService.save(product2);

        ProductFilters filters = ProductFilters.builder()
                .name(Optional.of("E"))
                .genreName(Optional.of("Fantasia"))
                .authorName(Optional.empty())
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(2, result.size());
        productService.deleteById(product.getId());
        productService.deleteById(product2.getId());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando apenas por gênero")
    public void testFindWithFiltersByGenre() {
        Product product = new Product();
        product.setName("Jurassic Park");
        product.setAuthorId(3);
        product.setGenreId(3);
        product.setDescription("Sinopse teste");
        productService.save(product);

        ProductFilters filters = ProductFilters.builder()
                .name(Optional.empty())
                .genreName(Optional.of("Terror"))
                .authorName(Optional.empty())
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(1, result.size());
        assertEquals("Jurassic Park", result.getFirst().getName());
        productService.deleteById(product.getId());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando por autor e gênero")
    public void testFindWithFiltersByAuthor() {
        Product product = new Product();
        product.setName("Eragon");
        product.setAuthorId(1);
        product.setGenreId(1);
        product.setDescription("Sinopse teste");
        productService.save(product);

        Product product2 = new Product();
        product2.setName("Eldest");
        product2.setAuthorId(1);
        product2.setGenreId(1);
        product2.setDescription("Sinopse teste 2");
        productService.save(product2);

        ProductFilters filters = ProductFilters.builder()
                .name(Optional.empty())
                .genreName(Optional.of("Fantasia"))
                .authorName(Optional.of("Christopher Paolini"))
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(2, result.size());
        productService.deleteById(product.getId());
        productService.deleteById(product2.getId());
    }
}
