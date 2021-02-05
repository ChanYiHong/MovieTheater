package ChanuE.MovieTheater.repository.seat;

import ChanuE.MovieTheater.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
