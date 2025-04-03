package com.swissre.servicea.service;


import com.swissre.servicea.dto.OrderDto;
import com.swissre.servicea.entities.OrderEntity;
import com.swissre.servicea.repositories.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class ServiceA {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceBClient serviceBClient;

    @Autowired
    private OrderRepository repository;

    /**
     * This method will handle both local transaction and calling Service B
     * with retry, timeout, and circuit breaker mechanisms.
     */
    @Transactional
    public void processRequest(@NonNull OrderDto orderRequest) {

        OrderEntity orderEntity = new OrderEntity(orderRequest.getOrderId(), orderRequest.getItem());
        repository.save(orderEntity);

        // Step 2: Call Service B with Resilience4j mechanisms for circuit breaker
        CompletableFuture<Void> serviceBCall = CompletableFuture.runAsync(() -> {
            callServiceB(orderRequest.getOrderId());
        });

        // We can wait for the service B call
        serviceBCall.join();
    }

    @Retry(name = "serviceBRetry", fallbackMethod = "fallbackForServiceB")
    @CircuitBreaker(name = "serviceBCircuitBreaker", fallbackMethod = "fallbackForServiceB")
    @TimeLimiter(name = "serviceBTimeLimiter", fallbackMethod = "fallbackForServiceB")
    public void callServiceB(Long orderId) {
        String response = serviceBClient.callServiceB(orderId);
        System.out.println("Received response form serviceB : "+response);
    }

    public void fallbackForServiceB(String userId, Throwable throwable) {
        // Handle the fallback logic, e.g., log error or use default values
        System.out.println("Fallback triggered: " + throwable.getMessage());
    }
}
