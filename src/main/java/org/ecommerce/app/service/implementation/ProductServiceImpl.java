package org.ecommerce.app.service.implementation;


import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.ProductPurchaseRequest;
import org.ecommerce.app.dto.ProductPurchaseResponse;
import org.ecommerce.app.entity.ProductEntity;
import org.ecommerce.app.exception.CreateOrderException;
import org.ecommerce.app.repo.ProductRepo;
import org.ecommerce.app.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public synchronized List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseList) {
        List<ProductPurchaseResponse> productPurchaseResponses = new ArrayList<>();
        Map<Long, ProductEntity> productsMap = new HashMap<>();
        List<Long> productIds = purchaseList.stream().map(ProductPurchaseRequest::productId).toList();
        List<ProductEntity> products = productRepo.findByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new CreateOrderException("Some of the products do not exist");
        } else {
            products.forEach(product -> productsMap.put(product.getId(), product));
        }

        purchaseList.forEach(request -> {
            ProductEntity productEntity = productsMap.get(request.productId());
            if (request.quantity() > productEntity.getQuantity()) {
                throw new CreateOrderException("Product " + productEntity.getName() + " is out of stock");
            }
        });

        for (ProductPurchaseRequest purchaseRequest : purchaseList) {
            ProductEntity product = productsMap.get(purchaseRequest.productId());
            product.setQuantity(product.getQuantity() - purchaseRequest.quantity());
            productPurchaseResponses.add(ProductPurchaseResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(purchaseRequest.quantity())
                    .totalPrice(product.getPrice().multiply(new BigDecimal(purchaseRequest.quantity())))
                    .build());
            productRepo.save(product);
        }

        return productPurchaseResponses;
    }

}
