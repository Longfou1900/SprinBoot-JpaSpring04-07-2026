package co.istad.longfou.ecommerce.features.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//public interface OrderRepository extends JpaRepository<Order, Integer> {
public interface OrderRepository extends JpaRepository<Order, UUID> {
    // JpaRepository<Order, UUID> matches @GeneratedValue(strategy = UUID) in Order entity
}
