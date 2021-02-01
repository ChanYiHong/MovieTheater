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
@ToString(exclude = {"theaters"})
public class Movie extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @Convert(converter = AgeLimitConverter.class)
    @Column(nullable = false)
    private AgeLimit ageLimit;

    private String description;

    private String director;

    private int runningTime;

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private List<Theater> theaters = new ArrayList<>();
}
