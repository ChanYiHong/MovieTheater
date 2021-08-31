package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r, m from Reservation r left join Member m where r.id = :id")
    Object[] getReservation(@Param("id") Long id);


}
