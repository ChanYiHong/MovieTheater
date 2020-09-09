package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MovieSaveRequestDto {

    private String name;

    @Builder
    public MovieSaveRequestDto(String name){
        this.name = name;
    }

    public Movie toEntity(){
        return Movie.builder().name(this.name).build();
    }
}
