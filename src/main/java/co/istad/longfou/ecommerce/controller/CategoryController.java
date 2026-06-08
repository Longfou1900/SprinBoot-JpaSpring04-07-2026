package co.istad.longfou.ecommerce.controller;

import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;
import co.istad.longfou.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    //TODO:
    private  final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createNew(createCategoryRequest);
    }
}
