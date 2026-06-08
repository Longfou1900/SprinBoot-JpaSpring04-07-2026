package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //for check category return f/t auto generate name
    boolean existsByName(String name);

}
