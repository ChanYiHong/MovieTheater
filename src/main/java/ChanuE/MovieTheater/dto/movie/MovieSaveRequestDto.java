package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieSaveRequestDto {

    private String name;

    public MovieSaveRequestDto(String name){
        this.name = name;
    }

    public Movie toEntity(){
        return Movie.builder().name(this.name).build();
    }
}
