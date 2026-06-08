package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //for check category return f/t auto generate name
    boolean existsByName(String name);

    Page<Category> finAllByIsDeleteFalse(Pageable pageable);

    Optional<Category> findByIdAndIsDeletedFalse(Integer id);

    List<Category> findAllByParentCategoryIdAndIsDeletedFalse(Integer parentId);

}
