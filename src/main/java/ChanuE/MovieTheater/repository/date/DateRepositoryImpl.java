package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.domain.QDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ChanuE.MovieTheater.domain.QDate.date;

@RequiredArgsConstructor
public class DateRepositoryImpl implements DateRepositoryCustom{

    private final JPAQueryFactory queryFactory;


}
