package org.ecommerce.app.service;

import org.ecommerce.app.dto.PaymentRequest;

public interface TransactionService {

    void readTransaction(PaymentRequest request);
}
