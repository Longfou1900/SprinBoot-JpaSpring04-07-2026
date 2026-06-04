package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
