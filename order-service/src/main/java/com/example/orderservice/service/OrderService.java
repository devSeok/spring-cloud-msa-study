package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public ResponseOrder createOrder(RequestOrder order, String userId) {

        OrderEntity orderEntity = order.toOrder(userId);
        OrderEntity save = orderRepository.save(orderEntity);

        return new ResponseOrder(save);
    }

    @Transactional(readOnly = true)
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity byOrderId = orderRepository.findByOrderId(orderId);

        return OrderDto.from(byOrderId);
    }
    @Transactional(readOnly = true)
    public List<ResponseOrder> getOrderByUserId(String userId) {
            return orderRepository.findByUserId(userId)
                    .stream()
                    .map(ResponseOrder::new)
                    .collect(Collectors.toList());
    }

}
