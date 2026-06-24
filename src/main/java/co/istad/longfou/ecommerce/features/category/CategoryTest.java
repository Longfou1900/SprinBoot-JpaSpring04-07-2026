package co.istad.longfou.ecommerce.features.category;

import co.istad.longfou.ecommerce.features.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table()
public class CategoryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String icon;

    @Column(nullable = false)
    private boolean is_Deleted;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "categoryTest")
    private List<Product> products;
}
