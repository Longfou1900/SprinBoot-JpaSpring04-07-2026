package co.istad.longfou.ecommerce.features.category.dto;

import lombok.Builder;

@Builder
public record CategorySnippetResponse(
        Integer id,
        String name
) {
}
