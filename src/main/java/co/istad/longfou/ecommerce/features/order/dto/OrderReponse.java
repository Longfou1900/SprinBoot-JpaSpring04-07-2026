package co.istad.longfou.ecommerce.features.order.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderReponse(
        UUID id,
        String customerId,
        String address,
        Float discount,
        String remark,
        Boolean status,
        LocalDateTime orderedAt,
        Boolean isDeleted
) {
}
