package ChanuE.MovieTheater.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieKobisApiSaveDTO {

    private String movieCode;
    private String title;
    private String openDate;
    private String genre;

}
