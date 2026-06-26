package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderLineDto;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import co.istad.longfou.ecommerce.features.product.Product;
import co.istad.longfou.ecommerce.features.product.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRespository productRepository;
    private final OrderLineDto orderLineDto;
    private final OrderLineRepository orderLineRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderReponse createNew(CreateOrderRequest createOrderRequest) {
        final Order order = new Order();
        order.setAddress(createOrderRequest.address());
        order.setDiscount(createOrderRequest.discount());
        order.setRemark(createOrderRequest.remark());

        List<OrderLine> orderLines = new ArrayList<>();

        //validation order lines()
        boolean isValidOrder = createOrderRequest.orderLines().stream()
                .allMatch(orderLine -> {
//                    Product product = productRepository.findByCode(orderLineDto.code())
//                            .orElseThrow(() ->
//                                    new ResponseStatusException(
//                                            HttpStatus.NOT_FOUND,
//                                            "Product code has not been found"
//                                    ));
                    Optional<Product> productOptional = productRepository
                            .findByCode(orderLineDto.code());

                    if (productOptional.isPresent()){
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnit_price(orderLineDto.unitPrice());
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;
                });
        if (!isValidOrder){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid order code line");
        }

        order.setOrderLines(orderLines);

        //Security related
        order.setCustomerId("ISTAD");

        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);

       Order savedOrder = orderRepository.save(order);

        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }
}
