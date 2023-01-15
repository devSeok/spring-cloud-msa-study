package com.example.orderservice.dto;


import com.example.orderservice.entity.OrderEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;

    public OrderEntity toOrder(String userId) {
        return OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .totalPrice(qty * unitPrice)
                .qty(qty)
                .unitPrice(unitPrice)
                .productId(productId)
                .userId(userId)
                .build();
    }
}
