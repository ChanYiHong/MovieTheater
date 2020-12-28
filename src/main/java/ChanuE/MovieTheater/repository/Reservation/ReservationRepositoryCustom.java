package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.Reservation;

import java.util.List;

public interface ReservationRepositoryCustom {
    public List<Reservation> findAllBySearchCond(ReservationSearch reservationSearch);
}
