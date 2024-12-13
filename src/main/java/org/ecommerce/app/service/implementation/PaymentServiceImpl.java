package org.ecommerce.app.service.implementation;

import lombok.RequiredArgsConstructor;
import org.ecommerce.app.dto.PaymentRequest;
import org.ecommerce.app.service.PaymentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void processPayment(PaymentRequest request) {
        rabbitTemplate.convertAndSend("payment-exchange", "payment-key", request);
    }
}
