package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r, m from Reservation r left join r.member m where r.id = :id")
    Object[] getReservation(@Param("id") Long id);

    @Query("select r from Reservation r left join r.member m where m.name = :name")
    List<Reservation> findByMemberName(@Param("name") String name);


}
