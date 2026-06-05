package co.istad.longfou.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;
    private boolean isDeleted;
    private String text;

    @Column(length = 4)
    @JoinColumn(name = "parentId")
    private Integer parentId;

    @Column(length = 4)
    private Integer videos;

    @ManyToOne
    private Video video;

}
