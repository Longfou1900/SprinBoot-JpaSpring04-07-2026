package co.istad.longfou.ecommerce.features.product;

import co.istad.longfou.ecommerce.features.category.Category;
import co.istad.longfou.ecommerce.features.category.CategoryRepository;
import co.istad.longfou.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.longfou.ecommerce.features.product.dto.ProductResponse;
import co.istad.longfou.ecommerce.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRespository productRepository;
    private final CategoryRepository categoryRepository;
    private final Productmapper productmapper;

    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Product> products = productRepository.findAll(pageRequest);

        return products.map(productmapper::mapProductToProductResponse);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {

        //validate product name
        if (productRepository.existsByName((createProductRequest.name()))){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Product name has already been used");
        }

        //validate category ID
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Category has not been found"));

        //transfer data from DTO to Model
        Product product = productmapper
                .mapCreateToProductRequestToProduct(createProductRequest);

        //Set generated system data
        product.setCategory(category);
//        product.setCode(""); //
        product.setCode(GenerateUtils.generateProductCode()); //
//        product.setSlug("");
        product.setSlug(GenerateUtils.generateSlug(createProductRequest.name()));
        product.setIsAvailable(true);
        product.setIsDeleted(false);

        product = productRepository.save(product);
        return productmapper.mapProductToProductResponse(product);
    }
}
