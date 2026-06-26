package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import co.istad.longfou.ecommerce.features.product.Product;
import co.istad.longfou.ecommerce.features.product.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRespository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderReponse createNew(CreateOrderRequest createOrderRequest) {
        final Order order = orderMapper.mapCreatedOrderedRequestToOrder(createOrderRequest);

        List<OrderLine> orderLines = new ArrayList<>();

        // use `orderLine` (stream param) not `orderLineDto` (missing field)
        boolean isValidOrder = createOrderRequest.orderLines().stream()
                .allMatch(orderLine -> {
                    Optional<Product> productOptional = productRepository
                            .findByCode(orderLine.code()); // use orderLine, not orderLineDto

                    if (productOptional.isPresent()) {
                        final OrderLine newOrderLine = new OrderLine();
                        newOrderLine.setProduct(productOptional.get());
                        newOrderLine.setQty(orderLine.qty());
                        newOrderLine.setUnit_price(orderLine.unitPrice());
                        newOrderLine.setOrder(newOrderLine.getOrder());
                        orderLines.add(newOrderLine);
                        return true;
                    }
                    return false;
                });

        if (!isValidOrder) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid order code line");
        }

        order.setOrderLines(orderLines);
        order.setCustomerId("ISTAD");
        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }

    @Override
    public Page<OrderReponse> findAll(int page, int size) {
        // 2 pagination + sort DESC by orderedAt
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "orderedAt"));
        Page<Order> orders = orderRepository.findAll(pageRequest);
        return orders.map(orderMapper::mapOrderToOrderResponse);
    }

    @Override
    public OrderReponse findById(UUID id) {
        // 3 find by ID
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
        return orderMapper.mapOrderToOrderResponse(order);
    }

    @Override
    public void softDeleteById(UUID id) {
        // 4 soft delete — sets isDeleted = true, does NOT remove from DB
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void hardDeleteById(UUID id) {
        // 5 hard delete — physically removes from DB
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
        orderRepository.delete(order);
    }

    @Override
    public OrderReponse setPaymentStatus(UUID id) {
        // 6 flip status false->true (PENDING -> PAID)
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
        order.setStatus(true);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }
}