package ChanuE.MovieTheater.repository.movieimage;

import ChanuE.MovieTheater.domain.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
