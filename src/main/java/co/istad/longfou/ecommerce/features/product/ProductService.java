package co.istad.longfou.ecommerce.features.product;

import co.istad.longfou.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.longfou.ecommerce.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<ProductResponse> findAll(int pageNumber, int pageSize);

    /**
     * Create a new product
     * @param createProductRequest is requesting data fore creating product
     * @return {@link ProductResponse}
     * @since 23-june-2026
     * @author tang_sengkim
     */
    ProductResponse createNew(CreateProductRequest createProductRequest);

}
