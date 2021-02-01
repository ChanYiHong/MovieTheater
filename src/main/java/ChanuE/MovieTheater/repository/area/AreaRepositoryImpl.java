package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QMovie;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static ChanuE.MovieTheater.domain.QArea.area;
import static ChanuE.MovieTheater.domain.QMovie.movie;

@RequiredArgsConstructor
public class AreaRepositoryImpl implements AreaRepositoryCustom{

    private final JPAQueryFactory queryFactory;



    private BooleanExpression movieNameEq(String movieName) {
        return StringUtils.hasText(movieName) ? movie.movieName.eq(movieName) : null;
    }
}
