package co.istad.longfou.ecommerce.repository;

import co.istad.longfou.ecommerce.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
