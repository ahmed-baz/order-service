package org.ecommerce.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class ProductEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;

}


