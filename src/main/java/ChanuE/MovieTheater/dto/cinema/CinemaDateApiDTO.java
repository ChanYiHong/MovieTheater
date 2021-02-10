package ChanuE.MovieTheater.dto.cinema;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CinemaDateApiDTO {

    private int year;
    private int month;
    private int day;

}
