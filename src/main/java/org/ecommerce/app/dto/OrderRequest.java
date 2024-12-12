package org.ecommerce.app.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(

        @NotEmpty(message = "products is required")
        List<ProductPurchaseRequest> products
) {
}
