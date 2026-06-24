package co.istad.longfou.ecommerce.features.product.dto;

import co.istad.longfou.ecommerce.features.category.dto.CategoryResponse;
import co.istad.longfou.ecommerce.features.category.dto.CategorySnippetResponse;
import jakarta.validation.constraints.*;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max=255)
        String name, //name as Slug e.g:Mouse Ligtech S4 -> mouse-logitech-s4
        @Size(max=255)
        String description,
        @Size(max=255)
        String thumbnail,
        @NotNull(message="Unit price is required")
        @Min(0)
        String unit_price,
        @NotNull(message="Qty price is required")
        @Min(0) //qty write can input from 0
        Integer qty,
        @NotNull(message="Category ID price is required")
        @Positive
        Integer categoryId
) {
}
