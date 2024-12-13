package org.ecommerce.app.repo;


import org.ecommerce.app.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends BaseRepo<OrderEntity> {

    List<OrderEntity> findByCreatedBy(String email);
}
