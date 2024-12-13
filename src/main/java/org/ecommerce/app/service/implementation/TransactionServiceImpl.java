package org.ecommerce.app.service.implementation;

import org.ecommerce.app.dto.PaymentRequest;
import org.ecommerce.app.service.TransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    @RabbitListener(queues = "payment-queue")
    public void readTransaction(PaymentRequest request) {
        System.out.println("Received request: " + request.orderId());
    }
}
