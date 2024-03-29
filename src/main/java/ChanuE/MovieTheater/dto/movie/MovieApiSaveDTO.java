package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieApiSaveDTO {

    private String title;
    private String director;
    private String image;
    private AgeLimit ageLimit;
    private Integer runningTime;
    private String description;

}
