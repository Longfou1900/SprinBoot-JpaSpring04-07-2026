package co.istad.longfou.ecommerce.service.iml;

import co.istad.longfou.ecommerce.domain.Category;
import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;
import co.istad.longfou.ecommerce.dto.UpdateCategoryRequest;
import co.istad.longfou.ecommerce.mapper.CategoryMapper;
import co.istad.longfou.ecommerce.repository.CategoryRepository;
import co.istad.longfou.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
//    private final CategoryMapper categoryMapper;

//    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
//        this.categoryRepository = categoryRepository;
//        this.categoryMapper = categoryMapper;
//    }

    //    private CategoryRepository categoryRepository;
    Category parentCategory = null;
    CategoryResponse parentCategoryResponse = null;

    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        log.info(" createNew {}", createCategoryRequest);
        //dto pattern(transfer) create category
        Category category = categoryMapper.mapCategoryRequestToCategory(createCategoryRequest);

        boolean isExisting = categoryRepository.existsByName(createCategoryRequest.name());

        if (isExisting)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "category has alrady been used"
            );
        if (createCategoryRequest.parentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category has not found"
                    ));
            category.setParentCategory(parentCategory);
        }


        //system generated data
        category.setIsDeleted(false);
//        category.setParentCategory(parentCategory);

        //Intsert if primary key is null
        //update if primary key have value
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getALlCategories(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Category> categories = categoryRepository.finAllByIsDeleteFalse(pageRequest);
        return categories.map(categoryMapper::mapCategoryToCategoryResponse);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found"
                ));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getSubCategoriesByMaincategoriesId(Integer id) {
        // Verify the main category exists and is not deleted
        categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found"
                ));
        List<Category> subcategories = categoryRepository
                .findAllByParentCategoryIdAndIsDeletedFalse(id);
        return subcategories.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse)
                .toList();
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found"
                ));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse softDeleteCategoryById(Integer id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found"
                ));
        category.setIsDeleted(true);
        category = categoryRepository.save(category);
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found"
                ));
        if (updateCategoryRequest.name() != null) {
            // Check name conflict only if name is being changed
            if (!updateCategoryRequest.name().equals(category.getName())) {
                boolean isExisting = categoryRepository.existsByName(updateCategoryRequest.name());
                if (isExisting) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "category has alrady been used"
                    );
                }
            }
            category.setName(updateCategoryRequest.name());
        }
        if (updateCategoryRequest.icon() != null) {
            category.setIcon(updateCategoryRequest.icon());
        }
        if (updateCategoryRequest.description() != null) {
            category.setDescription(updateCategoryRequest.description());
        }
        category = categoryRepository.save(category);
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
}
