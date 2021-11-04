package ChanuE.MovieTheater.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieApiDTO {

    private String title;
    private String director;
    private String image;

}
