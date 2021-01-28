package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationRepositoryCustom {
    Page<Object[]> findAllBySearchCond(ReservationSearch reservationSearch, Pageable pageable);
}
