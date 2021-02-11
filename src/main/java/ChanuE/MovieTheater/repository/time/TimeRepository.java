package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long>, TimeRepositoryCustom {

    @Query("select t from Time t left join t.cinema c where c.id = :cinemaId")
    List<Time> findByCinemaId(@Param("cinemaId") Long cinemaId);

}
