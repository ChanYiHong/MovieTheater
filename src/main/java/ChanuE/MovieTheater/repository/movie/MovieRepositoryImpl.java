package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.repository.CustomQuerydslUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static ChanuE.MovieTheater.domain.QMovie.movie;
import static ChanuE.MovieTheater.domain.QMovieImage.movieImage;
import static ChanuE.MovieTheater.domain.QReview.review;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Object[]> findAllBySearchCond(MovieSearch movieSearch, Pageable pageable) {
        QueryResults<Tuple> results = queryFactory
                .select(movie, movieImage, review.count(), review.grade.avg())
                .from(movie)
                .leftJoin(movieImage).on(movieImage.movie.eq(movie))
                .leftJoin(review).on(review.movie.eq(movie))
                .where(movieNameEq(movieSearch.getMovieName()))
                .orderBy(getOrderSpecifiers(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .groupBy(movie)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, total);
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
