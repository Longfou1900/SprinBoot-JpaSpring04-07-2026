package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

@Entity
@Table(name = "userprofile")
public class User {
    @Id
    @Column(name = "user_id_pk")
    private Long id;

    @Column(name = "user_email")
    private String email;

}
