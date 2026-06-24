package co.istad.longfou.ecommerce.features.category.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDeleted,
        CategoryResponse parentCategory
) {
}
