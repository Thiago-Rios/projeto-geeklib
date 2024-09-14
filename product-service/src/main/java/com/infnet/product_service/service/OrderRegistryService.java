package com.infnet.product_service.service;


import com.infnet.product_service.model.OrderRegistryPayload;
import com.infnet.product_service.model.Product;
import com.infnet.product_service.service.clients.OrderRegistryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRegistryService {
    private final OrderRegistryClient client;

    public OrderRegistryPayload createOrderRegistry(Product product) {
        String orderKey = product.generateOrderRegistryKey();
        return client.createOrderRegistry(new OrderRegistryPayload("Produto registrado", orderKey));
    }
}
