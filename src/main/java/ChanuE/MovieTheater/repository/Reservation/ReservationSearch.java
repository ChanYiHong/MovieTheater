package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationSearch {

    public String memberName;
    public String movieName;
    public ReservationStatus reservationStatus;

}
