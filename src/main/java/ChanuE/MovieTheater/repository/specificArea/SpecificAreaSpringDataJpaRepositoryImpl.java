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
public class SpecificAreaSpringDataJpaRepositoryImpl implements SpecificAreaRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SpecificArea> findSpecificAreaByAreaId(Long id) {

        return queryFactory
                .selectFrom(specificArea)
                .join(specificArea.area, area)
                .where(area.id.eq(id))
                .fetch();

    }

    @Override
    public List<SpecificArea> findAllBySearchCond(SpecificAreaSearch specificAreaSearch) {

        return queryFactory
                .selectFrom(specificArea)
                .leftJoin(specificArea.area, area)
                .where(
                        areaNameEq(specificAreaSearch.getAreaName())
                ).fetch();

    }

    private BooleanExpression areaNameEq(String areaName) {
        return StringUtils.hasText(areaName) ? area.areaName.eq(areaName) : null;
    }
}
