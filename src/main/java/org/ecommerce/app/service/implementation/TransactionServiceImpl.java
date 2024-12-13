package org.ecommerce.app.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.ecommerce.app.dto.PaymentRequest;
import org.ecommerce.app.entity.OrderEntity;
import org.ecommerce.app.entity.TransactionEntity;
import org.ecommerce.app.repo.OrderRepo;
import org.ecommerce.app.repo.TransactionRepo;
import org.ecommerce.app.service.TransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final OrderRepo orderRepo;

    @Override
    @RabbitListener(queues = "payment-queue")
    public void readTransaction(PaymentRequest request) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(request.orderId());
        if (orderEntity.isPresent()) {
            TransactionEntity transaction = TransactionEntity.builder()
                    .amount(request.amount())
                    .order(orderEntity.get())
                    .paymentMethod(request.paymentMethod())
                    .build();

            transactionRepo.save(transaction);
            log.info("The transaction with order {} is saved.", request.orderId());
        } else {
            log.error("Order with id {} not found.", request.orderId());
        }
    }
}
