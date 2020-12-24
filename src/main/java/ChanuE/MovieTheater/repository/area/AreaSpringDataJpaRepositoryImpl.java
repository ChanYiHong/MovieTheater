package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QMovie;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QArea.area;
import static ChanuE.MovieTheater.domain.QMovie.movie;

@RequiredArgsConstructor
public class AreaSpringDataJpaRepositoryImpl implements AreaRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Area> findAllAreaByMovieId(Long id) {
        List<Area> result = jpaQueryFactory
                .selectFrom(area)
                .join(area.movie, movie)
                .where(movie.id.eq(id))
                .fetch();

        return result;
    }
}
