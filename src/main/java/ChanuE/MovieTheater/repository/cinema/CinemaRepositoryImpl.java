package ChanuE.MovieTheater.repository.cinema;

import ChanuE.MovieTheater.domain.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static ChanuE.MovieTheater.domain.QCinema.cinema;
import static ChanuE.MovieTheater.domain.QDate.date;
import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QTheater.theater;


@RequiredArgsConstructor
public class CinemaRepositoryImpl implements CinemaRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cinema> findCinemaWithMovieTheaterDate(String movieName, String area, String specificArea, LocalDate localDate) {

        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .leftJoin(cinema.movie, movie).fetchJoin()
                .leftJoin(date).on(cinema.eq(date.cinema))
                .where(
                        movie.movieName.eq(movieName),
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea),
                        date.localDate.eq(localDate)
                )
                .fetch();
        return result;
    }

    @Override
    public List<Cinema> findCinemaWithMovieTheater(String movieName, String area, String specificArea) {
        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        movie.movieName.eq(movieName),
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea)
                )
                .fetch();
        return result;
    }

    @Override
    public List<Cinema> findCinemaWithMovie(String movieName) {
        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        movie.movieName.eq(movieName)
                )
                .fetch();
        return result;
    }

    @Override
    public List<Cinema> findCinemaWithTheater(String area, String specificArea) {
        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .where(
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea)
                )
                .fetch();
        return result;
    }
}
