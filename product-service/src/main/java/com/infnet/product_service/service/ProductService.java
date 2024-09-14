package com.infnet.product_service.service;

import com.infnet.product_service.filters.ProductFilters;
import com.infnet.product_service.model.Author;
import com.infnet.product_service.model.Genre;
import com.infnet.product_service.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> findById(Integer id);
    void deleteById(Integer id);
    Product save(Product product);
    Product update(Integer id, Product product);

    List<Product> findAllByName(String name);
    List<Product> findAllByNameContains(String name);
    List<Product> findAllFantasia();
    List<Product> findWithFilters(ProductFilters filters);
}
