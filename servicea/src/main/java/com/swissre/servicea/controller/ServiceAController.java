package com.swissre.servicea.controller;

import com.swissre.servicea.dto.OrderDto;
import com.swissre.servicea.service.ServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/serviceA")
public class ServiceAController {

    @Autowired
    private ServiceA serviceA;

    @PostMapping("/order")
    public ResponseEntity<String> processRequest(@RequestBody OrderDto orderRequest) {
        try {
            serviceA.processRequest(orderRequest);
            return ResponseEntity.ok("Order processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
