package ChanuE.MovieTheater.repository.cinema;

import ChanuE.MovieTheater.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long>, CinemaRepositoryCustom {

}
