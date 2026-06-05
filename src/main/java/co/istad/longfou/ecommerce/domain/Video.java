package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String duration;
    private boolean isDeleted;
    private boolean isPublished;
    private String slug;
    private String thumbnail;
    private String title;
    private String youtube;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "video")
    private List<Comment> comments;
}
