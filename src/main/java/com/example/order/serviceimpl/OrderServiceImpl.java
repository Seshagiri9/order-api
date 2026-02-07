package com.example.order.serviceimpl;

import com.example.order.dto.OrderDTO;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = OrderMapper.toEntity(dto);
        return OrderMapper.toDTO(repository.save(order));
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO dto) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setProductName(dto.getProductName());
        order.setQuantity(dto.getQuantity());
        order.setPrice(dto.getPrice());

        return OrderMapper.toDTO(repository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
