package org.ecommerce.app.controller;


import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.AppResponse;
import org.ecommerce.app.dto.OrderRequest;
import org.ecommerce.app.dto.OrderResponse;
import org.ecommerce.app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public AppResponse<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return new AppResponse<>(orderService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public AppResponse<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        return new AppResponse<>(orderService.update(id, request));
    }

    @GetMapping("/{id}")
    public AppResponse<OrderResponse> getOrder(@PathVariable Long id) {
        return new AppResponse<>(orderService.findById(id));
    }

    @GetMapping
    public AppResponse<List<OrderResponse>> getAllOrders() {
        return new AppResponse<>(orderService.findAll());
    }

    @DeleteMapping("/{id}")
    public AppResponse<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new AppResponse<>(HttpStatus.NO_CONTENT);
    }

}
