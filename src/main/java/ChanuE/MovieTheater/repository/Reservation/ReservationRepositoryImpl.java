package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.repository.CustomQuerydslUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ChanuE.MovieTheater.domain.QMember.member;
import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QReservation.reservation;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Object[]> findAllBySearchCond(ReservationSearch reservationSearch, Pageable pageable) {

        QueryResults<Tuple> result = queryFactory
                .select(reservation, member)
                .from(reservation)
                .leftJoin(reservation.member, member)
                .where(
                        memberEq(reservationSearch.getMemberName()),
                        movieEq(reservationSearch.getTitle()),
                        statusEq(reservationSearch.getReservationStatus())
                )
                .orderBy(getOrderSpecifiers(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList()), pageable, total);
    }

    private BooleanExpression memberEq(String searchMemberName) {
        return StringUtils.hasText(searchMemberName) ? member.name.containsIgnoreCase(searchMemberName) : null;
    }

    private BooleanExpression movieEq(String searchTitle) {
        return StringUtils.hasText(searchTitle) ? reservation.title.containsIgnoreCase(searchTitle) : null;
    }

    private BooleanExpression statusEq(ReservationStatus searchReservationStatus) {
        return searchReservationStatus != null ? reservation.status.eq(searchReservationStatus) : null;
    }

    private List<OrderSpecifier> getOrderSpecifiers(Sort sort){
        List<OrderSpecifier> orders = new ArrayList<>();
        sort.stream().forEach(order -> {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            switch(order.getProperty()){
                case "id":
                    OrderSpecifier<?> orderId = CustomQuerydslUtils.getSortedColumn(direction, reservation, "id");
                    orders.add(orderId);
                    break;
                default:
                    break;
            }
        });
        return orders;
    }
}
