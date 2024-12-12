package org.ecommerce.app.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String status,
        String reference,
        BigDecimal totalAmount
) {
}
