package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapCreatedOrderedRequestToOrder(CreateOrderRequest createOrderRequest);

    OrderReponse mapOrderToOrderResponse(Order order);

}
