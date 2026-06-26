package co.istad.longfou.ecommerce.features.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCode(String code);

    Boolean existsByName(String name);

}
