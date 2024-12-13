package org.ecommerce.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.ecommerce.app.enums.PaymentMethodEnum;

import java.util.List;

public record OrderRequest(

        @NotEmpty(message = "products is required")
        List<ProductPurchaseRequest> products,
        @NotNull(message = "payment method is required")
        PaymentMethodEnum paymentMethod
) {
}
