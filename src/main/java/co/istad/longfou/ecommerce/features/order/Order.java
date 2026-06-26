package co.istad.longfou.ecommerce.features.order;

import co.istad.longfou.ecommerce.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id_pk")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private String Address;
    @Column(nullable = false)
    private Float discount;
    private String remark;
    @Column(nullable = false)
    private Boolean status; // PENDING -> // PAYMENT
    @Column(nullable = false)
    private LocalDateTime orderedAt;
    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(name = "amount")
    private Double amount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines;

    //more Orders stored a User
    @ManyToOne
    @JoinColumn(name = "user_id_fk") // user_id_fk គឺជា Foreign Key នៅក្នុងដាតាបេស
    private User user;

}