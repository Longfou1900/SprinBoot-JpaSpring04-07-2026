package co.istad.longfou.ecommerce.service;

import co.istad.longfou.ecommerce.domain.Category;
import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);
}
