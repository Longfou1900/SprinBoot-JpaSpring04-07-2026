package co.istad.longfou.ecommerce.features.category;

import co.istad.longfou.ecommerce.features.category.dto.CategoryResponse;
import co.istad.longfou.ecommerce.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
//import org.springframework.stereotype.Component;

//@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //return is type = target
    //parameter = source
    Category mapCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

//    public Category mapCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest){
//        Category category = new Category();
//        category.setName(createCategoryRequest.name());
//        category.setDescription(createCategoryRequest.description());
//        category.setIcon(createCategoryRequest.icon());
//        return category;
//    }

    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
