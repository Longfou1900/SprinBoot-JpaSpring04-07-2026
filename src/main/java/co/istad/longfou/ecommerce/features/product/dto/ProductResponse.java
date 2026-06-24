package co.istad.longfou.ecommerce.features.product.dto;

import co.istad.longfou.ecommerce.features.category.dto.CategoryResponse;
import co.istad.longfou.ecommerce.features.category.dto.CategorySnippetResponse;

public record ProductResponse(
        String code,
        String slug,
        String name,
        String description,
        String thumbnail,
        String unitprice,
        Integer qty,
        Boolean isAvialable,
        Boolean isDeleted,
        CategoryResponse parentCategory,
        CategorySnippetResponse category
) {
}
