package org.ecommerce.app.dto;

import org.ecommerce.app.enums.PaymentMethodEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public record PaymentRequest(
        Long orderId,
        BigDecimal amount,
        PaymentMethodEnum paymentMethod
) implements Serializable {
}
