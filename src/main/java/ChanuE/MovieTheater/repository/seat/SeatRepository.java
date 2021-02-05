package ChanuE.MovieTheater.repository.seat;

import ChanuE.MovieTheater.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("select s from Seat s where s.time.id = :id")
    List<Seat> findByTimeId(@Param("id") Long timeId);

}
