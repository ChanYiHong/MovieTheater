package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.QCinema;
import ChanuE.MovieTheater.domain.QSeat;
import ChanuE.MovieTheater.domain.QTime;
import ChanuE.MovieTheater.domain.Time;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QCinema.cinema;
import static ChanuE.MovieTheater.domain.QSeat.seat;
import static ChanuE.MovieTheater.domain.QTime.time1;

@RequiredArgsConstructor
public class TimeRepositoryImpl implements TimeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /** To one 관계 (다 : 1, 1 : 1) 즉, Collection 객체를 가지지 않은 엔티티는 무조건 fetch join
     *  Collection은 fetch join 1개만 ! 1+N+N 문제 발생가능...
     *  그리고 distinct를 붙여줌.. 근데 이건 페이징이 불가능. 그치만 여기선 페이징이 필요없으므로 걍 사용. **/
    @Override
    public List<Time> findTimeByCinemaWithSeats(Long cinemaId) {
        List<Time> result = queryFactory
                .select(time1).distinct()
                .from(time1)
                .leftJoin(time1.cinema, cinema).fetchJoin()
                .leftJoin(seat).on(seat.time.eq(time1)).fetchJoin()
                .where(cinema.id.eq(cinemaId))
                .fetch();

        return result;
    }
}
