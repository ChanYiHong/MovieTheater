package ChanuE.MovieTheater.domain;

import ChanuE.MovieTheater.converter.AgeLimitConverter;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"movieImages"})
public class Movie extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Convert(converter = AgeLimitConverter.class)
    @Column(nullable = false)
    private AgeLimit ageLimit;

    @Lob
    private String description;

    private String director;

    private String image;

    private int runningTime;

    @Builder.Default
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieImage> movieImages = new ArrayList<>();

}
