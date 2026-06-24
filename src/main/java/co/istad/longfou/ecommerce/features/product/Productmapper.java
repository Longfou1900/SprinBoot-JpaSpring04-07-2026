package co.istad.longfou.ecommerce.features.product;

import co.istad.longfou.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.longfou.ecommerce.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Productmapper {

    Product mapCreateToProductRequestToProduct(CreateProductRequest createProductRequest);
    ProductResponse mapProductToProductResponse(Product product);

}
