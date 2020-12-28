package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationSearch {

    private String memberName;
    private String movieName;
    private ReservationStatus reservationStatus;

}
