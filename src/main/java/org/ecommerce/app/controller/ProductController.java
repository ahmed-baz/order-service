package org.ecommerce.app.controller;


import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.AppResponse;
import org.ecommerce.app.dto.Product;
import org.ecommerce.app.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/filter")
    public AppResponse<List<Product>> getProducts() {
        return new AppResponse<>(productService.getProducts());
    }

}
