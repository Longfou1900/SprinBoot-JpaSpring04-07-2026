package co.istad.longfou.ecommerce.features.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,
        String caption,
        Long size,
        String mediaType,
        //http://localhost:9990/me/istad.png
        String uri
//        String downloadUri
) {
}
