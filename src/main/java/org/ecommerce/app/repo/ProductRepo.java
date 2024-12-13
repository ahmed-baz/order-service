package org.ecommerce.app.repo;


import org.ecommerce.app.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends BaseRepo<ProductEntity> {

    List<ProductEntity> findByIdIn(List<Long> ids);
}
