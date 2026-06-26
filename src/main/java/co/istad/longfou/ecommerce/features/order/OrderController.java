package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.longfou.ecommerce.features.order.dto.OrderReponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //Create new order
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderReponse createNew(@Valid @RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createNew(createOrderRequest);
    }

    //Find all orders with pagination + sort DESC
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<OrderReponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return orderService.findAll(page, size);
    }

    //Find order by ID
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderReponse findById(@PathVariable UUID id) {
        return orderService.findById(id);
    }

    // Soft delete by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/soft-delete")
    public void softDeleteById(@PathVariable UUID id) {
        orderService.softDeleteById(id);
    }

    //Hard delete by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDeleteById(@PathVariable UUID id) {
        orderService.hardDeleteById(id);
    }

    // Set payment status by ID
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/status")
    public OrderReponse setPaymentStatus(@PathVariable UUID id) {
        return orderService.setPaymentStatus(id);
    }

}
