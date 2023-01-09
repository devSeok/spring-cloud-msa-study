package com.example.catalogservice.domain.dto.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class RequestCatalogDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
