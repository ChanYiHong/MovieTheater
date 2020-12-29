package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QMovie;
import ChanuE.MovieTheater.domain.QSpecificArea;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QArea.area;
import static ChanuE.MovieTheater.domain.QMovie.movie;

@RequiredArgsConstructor
public class MovieSpringDataJpaRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Movie> findAllBySearchCond(MovieSearch movieSearch) {
        return null;
    }
}
