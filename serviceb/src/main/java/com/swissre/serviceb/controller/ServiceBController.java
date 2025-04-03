package com.swissre.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/serviceB")
public class ServiceBController {

    @GetMapping("/notify")
    public String processRequest(@RequestParam Long orderId) {
        return "ServiceB notified for Order : " + orderId;
    }
}
