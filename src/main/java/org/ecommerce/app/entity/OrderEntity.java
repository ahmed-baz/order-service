package org.ecommerce.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ecommerce.app.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @OneToMany(mappedBy = "order")
    private List<OrderLineEntity> orderLines;

}


