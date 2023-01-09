package com.example.catalogservice.domain.service;


import com.example.catalogservice.domain.dto.response.ResponseCatalogDto;
import com.example.catalogservice.domain.entity.CatalogEntity;
import com.example.catalogservice.domain.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CatalogService {
    private final CatalogRepository catalogRepository;


    public List<ResponseCatalogDto> findAll() {
        return catalogRepository.findAll().stream()
                .map(ResponseCatalogDto::new)
                .collect(Collectors.toList());
    }
}
