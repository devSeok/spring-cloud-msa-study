package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;


    @GetMapping("{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable String userId) {

       return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderByUserId(userId));
    }



    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder order) {
        ResponseOrder responseOrder = orderService.createOrder(order, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseOrder);
    }


}
