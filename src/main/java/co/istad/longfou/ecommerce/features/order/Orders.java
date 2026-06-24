package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "order_id_pk")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    // ទំនាក់ទំនង៖ Order ច្រើន រក្សាទុកដោយ User តែម្នាក់
    @ManyToOne
    @JoinColumn(name = "user_id_fk") // user_id_fk គឺជា Foreign Key នៅក្នុងដាតាបេស
    private User user;

}