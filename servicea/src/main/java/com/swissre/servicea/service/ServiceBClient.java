package com.swissre.servicea.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceBClient {

    private final RestTemplate restTemplate;

    public ServiceBClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Call Service B to process the request for a given orderId.
     */
    public String callServiceB(Long orderId) {
        String url = "http://localhost:8081/api/serviceB/notify?orderId=" + orderId;
        return restTemplate.getForObject(url, String.class);
    }
}
