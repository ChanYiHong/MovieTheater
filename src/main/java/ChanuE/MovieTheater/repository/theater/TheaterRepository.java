package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Optional<Theater> findTheaterByAreaAndSpecificArea(String area, String specificArea);
}
