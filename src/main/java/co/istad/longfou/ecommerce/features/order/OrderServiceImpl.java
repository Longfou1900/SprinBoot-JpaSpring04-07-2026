package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderLineDto;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import co.istad.longfou.ecommerce.features.product.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository OrderLine;
    private final ProductRespository productRepository;
    private final OrderLineDto orderLineDto;

    @Override
    public OrderReponse createNew(CreateOrderRequest createOrderRequest) {

        //validation order lines()
        createOrderRequest.orderLines().stream()
                .allMatch(orderLine -> {
                    productRepository.findByCode(orderLineDto.code())
                })

        return null;
    }
}
