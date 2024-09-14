package com.infnet.product_service.service.clients;

import com.infnet.product_service.model.OrderRegistryPayload;
import com.infnet.product_service.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ORDERREGISTRY-SERVICE")
public interface OrderRegistryClient {
    @PostMapping("/")
    OrderRegistryPayload createOrderRegistry(@RequestBody OrderRegistryPayload payload);
}
