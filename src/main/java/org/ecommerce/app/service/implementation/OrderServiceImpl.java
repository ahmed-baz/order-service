package org.ecommerce.app.service.implementation;


import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.OrderRequest;
import org.ecommerce.app.dto.OrderResponse;
import org.ecommerce.app.dto.ProductPurchaseRequest;
import org.ecommerce.app.dto.ProductPurchaseResponse;
import org.ecommerce.app.entity.OrderEntity;
import org.ecommerce.app.entity.OrderLineEntity;
import org.ecommerce.app.enums.OrderStatusEnum;
import org.ecommerce.app.exception.OrderException;
import org.ecommerce.app.exception.OrderNotFoundException;
import org.ecommerce.app.mapper.OrderMapper;
import org.ecommerce.app.repo.OrderLineRepo;
import org.ecommerce.app.repo.OrderRepo;
import org.ecommerce.app.repo.ProductRepo;
import org.ecommerce.app.service.OrderService;
import org.ecommerce.app.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderLineRepo orderLineRepo;
    private final ProductService productService;

    @Override
    @Transactional
    public synchronized OrderResponse create(OrderRequest request) {

        OrderEntity orderEntity = initiateOrder();

        return createOrUpdate(request, orderEntity);
    }

    public synchronized OrderResponse createOrUpdate(OrderRequest request, OrderEntity orderEntity) {

        List<ProductPurchaseRequest> productPurchaseRequests = request.products();
        List<ProductPurchaseResponse> productPurchaseResponse = productService.purchaseProduct(productPurchaseRequests);

        orderEntity.setTotalAmount(getTotalAmount(productPurchaseResponse));
        orderEntity.setStatus(OrderStatusEnum.PURCHASING);
        orderRepo.save(orderEntity);

        //TODO DO PAYMENT

        saveOrderLine(productPurchaseResponse, orderEntity);

        orderEntity.setStatus(OrderStatusEnum.APPROVED);
        orderRepo.save(orderEntity);
        return orderMapper.toOrderResponse(orderEntity);
    }

    private BigDecimal getTotalAmount(List<ProductPurchaseResponse> productPurchaseResponses) {
        double sum = productPurchaseResponses.stream().mapToDouble(response -> response.totalPrice().doubleValue()).sum();
        return new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private OrderEntity initiateOrder() {
        OrderEntity orderEntity = OrderEntity.builder()
                .reference(UUID.randomUUID().toString())
                .status(OrderStatusEnum.PENDING).build();
        return this.orderRepo.save(orderEntity);
    }

    private void saveOrderLine(List<ProductPurchaseResponse> productPurchaseRes, OrderEntity orderEntity) {
        List<OrderLineEntity> orderLineList = new ArrayList<>();
        productPurchaseRes.forEach(product -> {
            var orderLine = OrderLineEntity
                    .builder()
                    .product(productRepo.findById(product.id()).get())
                    .order(orderEntity)
                    .quantity(product.quantity())
                    .build();
            orderLineList.add(orderLine);
        });
        orderLineRepo.saveAll(orderLineList);
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        OrderEntity orderEntity = getOrderById(id);
        OrderStatusEnum status = orderEntity.getStatus();
        switch (status) {
            case PENDING:
                return createOrUpdate(request, orderEntity);
            default:
                throw new OrderException("Cannot update order with status " + status);
        }
    }

    private OrderEntity getOrderById(Long id) {
        OrderEntity orderEntity = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        if (!orderEntity.getCreatedBy().equals(getUserName())) {
            throw new OrderNotFoundException(id);
        }
        return orderEntity;
    }


    @Override
    public void delete(Long id) {
        OrderEntity orderEntity = getOrderById(id);
        OrderStatusEnum status = orderEntity.getStatus();
        switch (status) {
            case PENDING:
                orderRepo.deleteById(id);
                break;
            case PURCHASING:
            case PENDING_PAYMENT:
            case PAYMENT_FAILED:
                orderEntity.setStatus(OrderStatusEnum.CANCELLED);
                orderRepo.save(orderEntity);
                break;
            default:
                throw new OrderException("Cannot delete order with status " + status);
        }
    }

    @Override
    public List<OrderResponse> findAll() {
        List<OrderEntity> orders = orderRepo.findByCreatedBy(getUserName());
        return orderMapper.toOrderResponse(orders);
    }

    @Override
    public OrderResponse findById(Long id) {
        OrderEntity orderEntity = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        if (!orderEntity.getCreatedBy().equals(getUserName())) {
            throw new OrderNotFoundException(id);
        }
        return orderMapper.toOrderResponse(orderEntity);
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
