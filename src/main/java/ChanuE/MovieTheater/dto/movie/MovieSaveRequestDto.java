package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieSaveRequestDto {

    private String name;
    private String director;
    private String description;
    private AgeLimit ageLimit;
    private int runningTime;

}
