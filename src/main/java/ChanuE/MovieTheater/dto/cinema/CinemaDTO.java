package ChanuE.MovieTheater.dto.cinema;

import ChanuE.MovieTheater.domain.Display;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CinemaDTO {

    private Long id;
    private String name;
    private String title;
    private String area;
    private String specificArea;
    private int seats;
    private Display display;
    private LocalDate date;

}
