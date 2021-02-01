package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QSpecificArea;
import ChanuE.MovieTheater.domain.SpecificArea;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static ChanuE.MovieTheater.domain.QArea.area;
import static ChanuE.MovieTheater.domain.QSpecificArea.specificArea;

@RequiredArgsConstructor
public class SpecificAreaRepositoryImpl implements SpecificAreaRepositoryCustom{

    private final JPAQueryFactory queryFactory;



    private BooleanExpression areaNameEq(String areaName) {
        return StringUtils.hasText(areaName) ? area.name.eq(areaName) : null;
    }
}
