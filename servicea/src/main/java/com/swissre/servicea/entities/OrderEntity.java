package com.swissre.servicea.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class OrderEntity {
    @Id
    private Long orderId;
    private String item;


    public OrderEntity() {

    }

    public OrderEntity(Long orderId, String item) {
        this.orderId = orderId;
        this.item = item;
    }

}

