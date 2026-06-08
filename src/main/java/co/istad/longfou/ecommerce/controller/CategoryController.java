package co.istad.longfou.ecommerce.controller;

import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;
import co.istad.longfou.ecommerce.dto.UpdateCategoryRequest;
import co.istad.longfou.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    //TODO:
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createNew(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize ){
        return categoryService.getALlCategories(pageNumber, pageNumber);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return  categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/subcategories")
    public List<CategoryResponse> getSubCategoriesByMainCategoryId(@PathVariable Integer id){
        return categoryService.getSubCategoriesByMaincategoriesId(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/{id}")
    public void deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse softDeleteCategoryById(@PathVariable Integer id) {
        return categoryService.softDeleteCategoryById(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id,
                                               @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.updateCategoryById(id, updateCategoryRequest);
    }
}
