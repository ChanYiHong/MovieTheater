package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.Movie;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class MovieSaveRequestDto {

    private String name;

    @Builder
    public MovieSaveRequestDto(String movieName){
        this.name = movieName;
    }

    public Movie toEntity(){
        return Movie.builder().movieName(this.name).build();
    }
}
