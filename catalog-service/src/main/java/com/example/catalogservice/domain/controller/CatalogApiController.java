package com.example.catalogservice.domain.controller;


import com.example.catalogservice.domain.dto.response.ResponseCatalogDto;
import com.example.catalogservice.domain.entity.CatalogEntity;
import com.example.catalogservice.domain.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogApiController {
    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<ResponseCatalogDto>> getCatalogs() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(catalogService.findAll());
    }
}
