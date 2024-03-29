package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "movie")
public class MovieImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String imgName;
    private String storeFileName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
//        movie.getMovieImages().add(this);
    }

}
