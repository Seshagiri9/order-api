package com.example.order.mapper;

import com.example.order.dto.OrderDTO;
import com.example.order.entity.Order;

public class OrderMapper {

    public static Order toEntity(OrderDTO dto) {
        return Order.builder()
                .id(dto.getId())
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }
}
