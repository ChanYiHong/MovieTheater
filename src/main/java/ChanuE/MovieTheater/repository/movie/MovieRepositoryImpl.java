package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QMovie;
import ChanuE.MovieTheater.domain.QSpecificArea;
import ChanuE.MovieTheater.repository.CustomQuerydslUtils;
import com.querydsl.core.QueryResults;
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

import static ChanuE.MovieTheater.domain.QArea.area;
import static ChanuE.MovieTheater.domain.QMovie.movie;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Movie> findAllBySearchCond(MovieSearch movieSearch, Pageable pageable) {
        QueryResults<Movie> results = queryFactory
                .selectFrom(movie)
                .where(movieNameEq(movieSearch.getMovieName()))
                .orderBy(getOrderSpecifiers(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Movie> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression movieNameEq(String movieName) {
        return (StringUtils.hasText(movieName)) ? movie.movieName.containsIgnoreCase(movieName) : null;
    }

    private List<OrderSpecifier> getOrderSpecifiers(Sort sort){
        List<OrderSpecifier> orders = new ArrayList<>();
        sort.stream().forEach(order -> {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            switch(order.getProperty()){
                case "id":
                    OrderSpecifier<?> orderId = CustomQuerydslUtils.getSortedColumn(direction, movie, "id");
                    orders.add(orderId);
                    break;
                default:
                    break;
            }
        });
        return orders;
    }
}
