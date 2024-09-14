package com.infnet.product_service.controller;

import com.infnet.product_service.exception.ResourceNotFoundException;
import com.infnet.product_service.model.Product;
import com.infnet.product_service.payload.MessagePayload;
import com.infnet.product_service.service.OrderRegistryService;
import com.infnet.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;
    private final OrderRegistryService orderRegistryService;

    public ProductController(ProductService productService, OrderRegistryService orderRegistryService) {
        this.productService = productService;
        this.orderRegistryService = orderRegistryService;
    }

    @Operation(summary = "Busca todos os produtos")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @Operation(summary = "Busca um produto pela ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<MessagePayload> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        orderRegistryService.createOrderRegistry(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @Operation(summary = "Deleta um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Produto deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Atualiza um produto específico")
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            productService.update(id,product);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }

    }
}

