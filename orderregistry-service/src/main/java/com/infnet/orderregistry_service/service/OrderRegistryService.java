package com.infnet.orderregistry_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infnet.orderregistry_service.rabbitmq.OrderRegistryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRegistryService {
    private final OrderRegistryProducer producer;
    public void createOrderRegistry(String orderKey) throws JsonProcessingException {
        producer.send(orderKey);
    }
}
