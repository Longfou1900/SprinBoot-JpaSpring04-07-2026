package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;

public interface OrderService {

    OrderReponse createNew(CreateOrderRequest createOrderRequest);

}
