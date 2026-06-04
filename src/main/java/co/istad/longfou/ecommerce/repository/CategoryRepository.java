package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
