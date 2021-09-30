package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSearch {

    private String memberName;
    private String title;
    private ReservationStatus reservationStatus;

}
