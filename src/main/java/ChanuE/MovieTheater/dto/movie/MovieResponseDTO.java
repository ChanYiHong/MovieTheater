package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movieimage.MovieImageDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieResponseDTO {

    private Long id;
    private String title;
    private AgeLimit ageLimit;
    private String description;
    private String director;
    private int runningTime;

    private MovieImageDTO movieImageDTO;

    private String image;

    private int reviewCnt;
    private double gradeAvg;

}
