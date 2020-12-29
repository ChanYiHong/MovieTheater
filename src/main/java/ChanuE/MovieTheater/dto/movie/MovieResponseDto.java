package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.Movie;
import lombok.*;

@Data
@NoArgsConstructor
public class MovieResponseDto {

    private Long id;
    private String movieName;

    @Builder
    public MovieResponseDto(Movie movie){
        this.id = movie.getId();
        this.movieName = movie.getMovieName();
    }
}
