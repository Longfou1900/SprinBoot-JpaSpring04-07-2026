package co.istad.longfou.ecommerce.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDeleted,
        CategoryResponse parentCategory
) {
}
