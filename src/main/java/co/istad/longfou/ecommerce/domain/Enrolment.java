package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "enrolments")
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime enrolledAt;
    private LocalDateTime paymentAt;
    private String paymentMethod;
    private boolean paymentStatus;

    @ManyToOne
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile studentProfile;
}
