package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Area {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    private String name;

    @Builder
    public Area(String name){
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // == 연관 관계 메서드 == //
    public void setMovie(Movie movie){
        this.movie = movie;
        movie.getAreas().add(this);
    }

}
