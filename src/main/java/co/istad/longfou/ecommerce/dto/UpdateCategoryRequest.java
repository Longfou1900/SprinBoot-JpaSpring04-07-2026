package co.istad.longfou.ecommerce.dto;

import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @Size(max = 50)
        String name,
        @Size(max = 255)
        String icon,
        String description
) {
}
