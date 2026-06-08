package co.istad.longfou.ecommerce.service.iml;

import co.istad.longfou.ecommerce.domain.Category;
import co.istad.longfou.ecommerce.dto.CategoryResponse;
import co.istad.longfou.ecommerce.dto.CreateCategoryRequest;
import co.istad.longfou.ecommerce.mapper.CategoryMapper;
import co.istad.longfou.ecommerce.repository.CategoryRepository;
import co.istad.longfou.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        //dto pattern(transfer ) create category
        Category category = categoryMapper
                .mapCategoryRequestToCategory(createCategoryRequest);
        boolean isExisting = categoryRepository
                .existsByName(createCategoryRequest.name());
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
}
