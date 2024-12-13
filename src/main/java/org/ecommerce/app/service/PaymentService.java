package org.ecommerce.app.service;

import org.ecommerce.app.dto.PaymentRequest;

public interface PaymentService {

    void processPayment(PaymentRequest request);
}
