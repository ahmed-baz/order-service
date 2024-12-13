package org.ecommerce.app.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(
        Long id,
        String name,
        BigDecimal price,
        Long quantity,
        BigDecimal totalPrice
) {
}
