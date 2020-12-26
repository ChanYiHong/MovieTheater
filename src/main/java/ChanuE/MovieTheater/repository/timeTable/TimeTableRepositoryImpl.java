package ChanuE.MovieTheater.repository.timeTable;

import ChanuE.MovieTheater.domain.QDate;
import ChanuE.MovieTheater.domain.QTimeTable;
import ChanuE.MovieTheater.domain.TimeTable;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QDate.date;
import static ChanuE.MovieTheater.domain.QTimeTable.timeTable;

@RequiredArgsConstructor
public class TimeTableRepositoryImpl implements TimeTableRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TimeTable> findAllTimeTableByDateId(Long id) {
        return jpaQueryFactory
                .selectFrom(timeTable)
                .join(timeTable.date, date)
                .where(date.id.eq(id))
                .fetch();
    }
}
