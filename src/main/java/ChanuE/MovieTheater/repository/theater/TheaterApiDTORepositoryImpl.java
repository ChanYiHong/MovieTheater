package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.domain.QCinema;
import ChanuE.MovieTheater.domain.QMovie;
import ChanuE.MovieTheater.domain.QTheater;
import ChanuE.MovieTheater.dto.theater.QTheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.QTheaterSpecAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QCinema.cinema;
import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QTheater.theater;

@RequiredArgsConstructor
public class TheaterApiDTORepositoryImpl implements TheaterApiDTORepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TheaterAreaApiDTO> findAllArea(Long movieId) {

        List<TheaterAreaApiDTO> result = queryFactory
                .select(new QTheaterAreaApiDTO(
                        theater.area,
                        theater.specificArea.count()
                ))
                .from(cinema)
                .leftJoin(cinema.theater, theater)
                .leftJoin(cinema.movie, movie)
                .where(movie.id.eq(movieId))
                .groupBy(theater.area)
                .orderBy(theater.id.asc())
                .fetch();
        return result;

    }

    @Override
    public List<TheaterSpecAreaApiDTO> findAllSpecificAreaByArea(String area, Long movieId) {
        List<TheaterSpecAreaApiDTO> result = queryFactory
                .select(new QTheaterSpecAreaApiDTO(
                        theater.area,
                        theater.specificArea
                )).from(cinema)
                .leftJoin(cinema.theater, theater)
                .leftJoin(cinema.movie, movie)
                .where(theater.area.eq(area), movie.id.eq(movieId))
                .groupBy(theater.specificArea)
                .orderBy(theater.id.asc())
                .fetch();

        return result;
    }
}
