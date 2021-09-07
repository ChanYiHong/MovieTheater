package ChanuE.MovieTheater.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDTO {

    private String movieName;
    private String content;
    private Integer grade;

}
