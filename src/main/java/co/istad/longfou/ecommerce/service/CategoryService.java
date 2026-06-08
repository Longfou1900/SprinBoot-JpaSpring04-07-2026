package co.istad.longfou.ecommerce.service;

import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;
import co.istad.longfou.ecommerce.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> getALlCategories(Integer pageNumber, Integer pageSize);

    CategoryResponse getCategoryById(Integer id);

    List<CategoryResponse> getSubCategoriesByMaincategoriesId(Integer id);

    void deleteCategoryById(Integer id);

    CategoryResponse softDeleteCategoryById(Integer id);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryResponse);
}
