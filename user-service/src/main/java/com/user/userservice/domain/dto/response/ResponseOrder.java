package com.user.userservice.domain.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;
    private String orderId;
}
