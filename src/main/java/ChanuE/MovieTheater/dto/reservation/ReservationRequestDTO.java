package ChanuE.MovieTheater.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationRequestDTO {

    private Integer totalPerson;
    private List<Long> seatList;
    private Long movieId;
    private String area;
    private String specific;
    private Integer year;
    private Integer month;
    private Integer day;

    private Long timeId;

}
