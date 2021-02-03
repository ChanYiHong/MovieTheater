package ChanuE.MovieTheater.dto.theater;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TheaterDTO {

    private Long id;
    private String name;
    private String area;
    private String specificArea;

}
