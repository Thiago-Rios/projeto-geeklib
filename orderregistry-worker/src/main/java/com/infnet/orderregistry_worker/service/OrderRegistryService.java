package com.infnet.orderregistry_worker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class OrderRegistryService {

    public void processOrderRegistry(String orderKey) {
        try {
            Thread.sleep(10_000);
            log.info("Order Registry received: " + orderKey);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
