package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static ChanuE.MovieTheater.domain.QMember.member;
import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QReservation.reservation;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> findAllBySearchCond(ReservationSearch reservationSearch) {

        List<Reservation> result = queryFactory
                .selectFrom(reservation)
                .join(reservation.member, member).fetchJoin()
                .join(reservation.movie, movie).fetchJoin()
                .where(
                        memberEq(reservationSearch.getMemberName()),
                        movieEq(reservationSearch.getMovieName()),
                        statusEq(reservationSearch.getReservationStatus())
                )
                .fetch();

        return result;
    }

    private BooleanExpression memberEq(String searchMemberName) {
        return StringUtils.hasText(searchMemberName) ? reservation.member.memberName.eq(searchMemberName) : null;
    }

    private BooleanExpression movieEq(String searchMovieName) {
        return StringUtils.hasText(searchMovieName) ? reservation.movie.movieName.eq(searchMovieName) : null;
    }

    private BooleanExpression statusEq(ReservationStatus searchReservationStatus) {
        return searchReservationStatus != null ? reservation.status.eq(searchReservationStatus) : null;
    }
}
