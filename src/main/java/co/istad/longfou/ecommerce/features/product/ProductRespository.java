package co.istad.longfou.ecommerce.features.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product, Integer> {

    Boolean existsByName(String name);

}
