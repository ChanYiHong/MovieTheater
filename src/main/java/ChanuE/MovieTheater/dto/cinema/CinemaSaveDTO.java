package ChanuE.MovieTheater.dto.cinema;

import ChanuE.MovieTheater.domain.Display;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaSaveDTO {

    private Long id;
    private String name;
    private String movieName;
    private int seats;
    private int year;
    private int month;
    private int day;
    private Display display;
}
