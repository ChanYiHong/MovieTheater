package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieResponseDTO {

    private Long id;
    private String movieName;
    private AgeLimit ageLimit;
    private String description;
    private String director;
    private int runningTime;

    private MovieImage movieImage;

    private int reviewCnt;
    private double gradeAvg;

}
