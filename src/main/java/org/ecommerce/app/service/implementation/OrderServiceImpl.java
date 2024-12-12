package org.ecommerce.app.service.implementation;


import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.OrderRequest;
import org.ecommerce.app.dto.OrderResponse;
import org.ecommerce.app.mapper.OrderMapper;
import org.ecommerce.app.repo.OrderRepo;
import org.ecommerce.app.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;

    @Override
    public OrderResponse create(OrderRequest request) {
        return null;
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderResponse> findAll() {
        return List.of();
    }

    @Override
    public OrderResponse findById(Long id) {
        return null;
    }
}
