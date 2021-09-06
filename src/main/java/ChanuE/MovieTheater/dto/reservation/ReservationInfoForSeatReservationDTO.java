package ChanuE.MovieTheater.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationInfoForSeatReservationDTO {

    private Long movieId;
    private String area;
    private String specific;
    private String year;
    private String month;
    private String day;

}
