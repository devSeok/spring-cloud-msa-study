package com.example.catalogservice.domain.dto.response;

import com.example.catalogservice.domain.entity.CatalogEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalogDto {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDateTime cratedAt;

    public ResponseCatalogDto(CatalogEntity catalog) {
        this.productId = catalog.getProductId();
        this.productName = catalog.getProductName();
        this.unitPrice = catalog.getUnitPrice();
        this.stock = catalog.getStock();
        this.cratedAt = catalog.getCreatedAt();
    }
}
