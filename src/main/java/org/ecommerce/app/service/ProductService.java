package org.ecommerce.app.service;

import org.ecommerce.app.dto.ProductPurchaseRequest;
import org.ecommerce.app.dto.ProductPurchaseResponse;

import java.util.List;

public interface ProductService {

    List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseList);
}
