package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.domain.QDate;
import ChanuE.MovieTheater.domain.QSpecificArea;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QDate.date;
import static ChanuE.MovieTheater.domain.QSpecificArea.specificArea;

@RequiredArgsConstructor
public class DateSpringDataJpaRepositoryImpl implements DateRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Date> findAllDateBySpecificAreaId(Long id) {

        return queryFactory
                .selectFrom(date)
                .join(date.specificArea, specificArea)
                .where(specificArea.id.eq(id))
                .fetch();
    }
}
