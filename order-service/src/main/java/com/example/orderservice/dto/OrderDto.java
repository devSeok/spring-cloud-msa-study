package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;


    public OrderEntity toOrder() {
        return OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .totalPrice(qty * unitPrice)
                .productId(productId)
                .userId(userId)
                .build();
    }

    public static OrderDto from(OrderEntity order) {
        return new OrderDto(
                order.getProductId(),
                order.getQty(),
                order.getUnitPrice(),
                order.getTotalPrice(),
                order.getOrderId(),
                order.getUserId()
        );
    }
}
