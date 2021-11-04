package ChanuE.MovieTheater.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRatingHomeViewDTO {

    private String title;
    private Double rating;
    private String image;


}
