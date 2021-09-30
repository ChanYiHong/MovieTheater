package ChanuE.MovieTheater.repository.cinema;

import ChanuE.MovieTheater.domain.*;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static ChanuE.MovieTheater.domain.QCinema.cinema;

import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QTheater.theater;


@RequiredArgsConstructor
@Log4j2
public class CinemaRepositoryImpl implements CinemaRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cinema> findCinemaWithMovieTheaterDate(String title, String area, String specificArea, LocalDate localDate) {

        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        movie.title.eq(title),
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea),
                        cinema.date.eq(localDate)
                )
                .fetch();
        return result;
    }

    @Override
    public List<Cinema> findCinemaWithMovieTheater(String title, String area, String specificArea) {
        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        movie.title.eq(title),
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea)
                )
                .fetch();
        return result;
    }

    @Override
    public List<Cinema> findCinemaWithMovie(String title) {
        List<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        movie.title.eq(title)
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

    @Override
    public Page<Cinema> findCinemaWithSearchCond(Long theaterId, CinemaSearch cinemaSearch, Pageable pageable) {
        QueryResults<Cinema> result = queryFactory
                .selectFrom(cinema)
                .leftJoin(cinema.theater, theater).fetchJoin()
                .leftJoin(cinema.movie, movie).fetchJoin()
                .where(
                        theater.id.eq(theaterId),
                        //dateEq(cinemaSearch.getDate()),
                        yearEq(cinemaSearch.getYear()),
                        monthEq(cinemaSearch.getMonth()),
                        dayEq(cinemaSearch.getDay())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cinema.id.asc()) // id 오름차순으로 고정.
                .fetchResults();

        List<Cinema> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<LocalDate> findCinemaDateForAPI(Long movieId, String area, String specificArea) {
        return queryFactory
                .select(cinema.date)
                .from(cinema)
                .leftJoin(cinema.movie, movie)
                .leftJoin(cinema.theater, theater)
                .where(
                        movie.id.eq(movieId),
                        theater.area.eq(area),
                        theater.specificArea.eq(specificArea)
                )
                .orderBy(cinema.date.asc())
                .fetch();
    }

    private BooleanExpression yearEq(int year) {
        return year != 0 ? cinema.date.year().eq(year) : null;
    }

    private BooleanExpression monthEq(int month) {
        return month != 0 ? cinema.date.month().eq(month) : null;
    }

    private BooleanExpression dayEq(int day) {
        return day != 0 ? cinema.date.dayOfMonth().eq(day) : null;
    }

    private BooleanExpression dateEq(LocalDate date) {
        return date != null ? cinema.date.eq(date) : null;
    }
}
