package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
