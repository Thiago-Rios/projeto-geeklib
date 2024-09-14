package com.infnet.product_service.repository;

import com.infnet.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name);
    List<Product> findAllByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.genreName = 'Fantasia'")
    List<Product> findAllFantasia();
}

