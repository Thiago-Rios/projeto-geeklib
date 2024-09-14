package com.infnet.orderregistry_service.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderRegistryProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void send(String orderKey) throws JsonProcessingException {
        Map<String, Object> messagePayload = new HashMap<>();
        messagePayload.put("orderKey", orderKey);
        amqpTemplate.convertAndSend("order-registry-exc", "order-registry-rk", objectMapper.writeValueAsString(messagePayload));
    }
}
