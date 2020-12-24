package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSpringDataJpaRepository extends JpaRepository<Movie, Long> {

    public List<Movie> findMovieByMovieName(String name);
}
