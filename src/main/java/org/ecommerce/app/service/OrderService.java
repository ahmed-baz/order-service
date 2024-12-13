package org.ecommerce.app.service;


import org.ecommerce.app.dto.OrderRequest;
import org.ecommerce.app.dto.OrderResponse;
import org.ecommerce.app.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse createOrUpdate(OrderRequest request, OrderEntity orderEntity);

    OrderResponse update(Long id, OrderRequest request);

    void delete(Long id);

    List<OrderResponse> findAll();

    List<OrderResponse> findAll(String email);

    OrderResponse findById(Long id);

}
