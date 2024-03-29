package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieRepositoryCustom {
    Page<Object[]> findAllBySearchCond(MovieSearch movieSearch, Pageable pageable);

    List<Object[]> findMovieWithAvgRatingForHomeView();
}
