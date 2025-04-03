package com.swissre.servicea.dto;

import lombok.*;


public class OrderDto {
    private Long orderId;

    public OrderDto(Long orderId, String item) {
        this.orderId = orderId;
        this.item = item;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    private String item;
}
