package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"areas"})
public class Movie extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @OneToMany(mappedBy = "movie")
    @Builder.Default
    private List<Area> areas = new ArrayList<>();

}
