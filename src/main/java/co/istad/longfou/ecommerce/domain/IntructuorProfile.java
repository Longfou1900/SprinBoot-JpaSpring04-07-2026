package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "intructuorProfiles")
public class IntructuorProfile {
    @Id
    private Integer userId;
    private String biography;
    private String facebookLink;
    private String githubLink;
    private String jobTitle;
    private String phone_number;

    @ManyToOne
    private Course course;

}
