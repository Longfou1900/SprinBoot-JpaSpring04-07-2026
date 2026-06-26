package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderReponse createNew(CreateOrderRequest createOrderRequest);

    Page<OrderReponse> findAll(int page, int size);                         // Task 2

    OrderReponse findById(UUID id);                                          // Task 3

    void softDeleteById(UUID id);                                            // Task 4

    void hardDeleteById(UUID id);                                            // Task 5

    OrderReponse setPaymentStatus(UUID id);
}
