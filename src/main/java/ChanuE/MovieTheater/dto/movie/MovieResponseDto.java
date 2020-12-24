package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MovieResponseDto {

    private Long id;
    private String name;

    public MovieResponseDto(Movie movie){
        this.id = movie.getId();
        this.name = movie.getMovieName();
    }

    public static List<MovieResponseDto> movieToMovieResponseDtos(List<Movie> movies){
        List<MovieResponseDto> dtos = new ArrayList<>();
        for(Movie m : movies){
            dtos.add(new MovieResponseDto(m));
        }
        return dtos;
    }

    public static MovieResponseDto movieToMovieResponseDto(Movie movie){
        return new MovieResponseDto(movie);
    }

}
