package org.ecommerce.app.mapper;


import org.ecommerce.app.dto.OrderResponse;
import org.ecommerce.app.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderResponse toOrderResponse(OrderEntity orderEntity);

    List<OrderResponse> toOrderResponse(List<OrderEntity> orders);
}
