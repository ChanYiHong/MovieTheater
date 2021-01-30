package ChanuE.MovieTheater.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {

    private Long id;
    private Long movieId;
    private String content;
    private String writer;
    private int grade;

}
