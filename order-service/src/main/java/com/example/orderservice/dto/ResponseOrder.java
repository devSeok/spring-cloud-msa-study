package com.example.orderservice.dto;


import com.example.orderservice.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime cratedAt;

    private String orderId;

    public ResponseOrder(OrderEntity order) {
        this.productId = order.getProductId();
        this.qty = order.getQty();
        this.unitPrice = order.getUnitPrice();
        this.totalPrice = order.getTotalPrice();
        this.cratedAt = order.getCreatedAt();
        this.orderId = order.getOrderId();
    }
}
