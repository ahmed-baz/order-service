package org.ecommerce.app.entity;


import jakarta.persistence.*;
import lombok.*;
import org.ecommerce.app.enums.PaymentMethodEnum;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
@Entity(name = "transactions")
public class TransactionEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
}
