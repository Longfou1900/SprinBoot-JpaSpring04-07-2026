package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer countRating;

    @Column(length = 6)
    private LocalDateTime createdAt;
    private String description;
    private Integer discountPercent;
    private boolean isDeleted;
    private boolean isPublished;
    private String keyword;
    private Integer level;

    @Column()
    private Double price;
    @Column(length = 4)
    private Float starRating;

    private String thumbnail;

    private String title;

    @Column(length = 4)
    private Float totalHour;

    @Column(length = 6)
    private LocalDateTime updatedAt;

//    @ManyToOne
//    private Category category;

    @OneToMany(mappedBy = "course")
    private List<IntructuorProfile> intructuorProfiles;

    @OneToMany(mappedBy = "course")
    private List<Enrolment> enrolments;
}
