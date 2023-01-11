package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto createOrder(OrderDto orderDto) {

        OrderEntity orderEntity = orderDto.toOrder();
        orderRepository.save(orderEntity);

        return orderDto;
    }

    @Transactional(readOnly = true)
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity byOrderId = orderRepository.findByOrderId(orderId);


        return ;
    }
    @Transactional(readOnly = true)
    public List<OrderEntity> getOrderByUserId(String userId) {
            return orderRepository.findByUserId(userId);
    }

}
