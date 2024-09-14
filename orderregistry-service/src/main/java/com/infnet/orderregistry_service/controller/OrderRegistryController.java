package com.infnet.orderregistry_service.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.infnet.orderregistry_service.service.OrderRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class OrderRegistryController {
    private final OrderRegistryService orderRegistryService;

    @PostMapping
    public ResponseEntity<Map<String, String>> generateOrderRegistry(@RequestBody String orderKey) {

        try {
            orderRegistryService.createOrderRegistry(orderKey);
        } catch (JsonProcessingException e) {
            ResponseEntity.internalServerError().build();
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(Map.of("Message","Ordem de Registro criada com sucesso!"));
    }
}
