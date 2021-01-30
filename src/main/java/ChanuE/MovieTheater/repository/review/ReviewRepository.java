package ChanuE.MovieTheater.repository.review;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByMovie(Movie movie, Pageable pageable);

}
