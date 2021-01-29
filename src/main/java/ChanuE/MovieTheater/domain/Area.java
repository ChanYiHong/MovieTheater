package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "specificAreas"})
@Entity
public class Area extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    private String areaName;

    @Builder
    public Area(String areaName){
        this.areaName = areaName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "area")
    List<SpecificArea> specificAreas = new ArrayList<>();

    // == 연관 관계 메서드 == //
    public void setMovie(Movie movie){
        this.movie = movie;
        movie.getAreas().add(this);
    }

}
