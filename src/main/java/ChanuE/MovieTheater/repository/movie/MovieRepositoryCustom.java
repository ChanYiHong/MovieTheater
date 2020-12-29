package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;

import java.util.List;

public interface MovieRepositoryCustom {
    public List<Movie> findAllBySearchCond(MovieSearch movieSearch);
}
