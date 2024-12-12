package org.ecommerce.app.service;


import org.ecommerce.app.dto.OrderRequest;
import org.ecommerce.app.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse update(Long id, OrderRequest request);

    void delete(Long id);

    List<OrderResponse> findAll();

    OrderResponse findById(Long id);

}
