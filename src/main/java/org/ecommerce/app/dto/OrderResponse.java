package org.ecommerce.app.dto;

import org.ecommerce.app.enums.OrderStatusEnum;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        OrderStatusEnum status,
        String reference,
        BigDecimal totalAmount
) {
}
