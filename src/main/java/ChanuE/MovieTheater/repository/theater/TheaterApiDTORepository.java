package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterApiDTORepository extends JpaRepository<Theater, Long>, TheaterApiDTORepositoryCustom {
}
