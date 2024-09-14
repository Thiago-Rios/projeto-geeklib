package com.infnet.orderregistry_worker.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.orderregistry_worker.service.OrderRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderRegistryConsumer {
    private final ObjectMapper objectMapper;
    private final OrderRegistryService orderRegistryService;

    @RabbitListener(queues = {"order-registry-queue"}, ackMode = "AUTO")
    public void receive(@Payload String json){
        try {
            Map<String, Object> messagePayload = objectMapper.readValue(json, Map.class);
            String orderKey = (String) messagePayload.get("orderKey");
            log.info("Order Registry created: " + orderKey);
            orderRegistryService.processOrderRegistry(orderKey);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
