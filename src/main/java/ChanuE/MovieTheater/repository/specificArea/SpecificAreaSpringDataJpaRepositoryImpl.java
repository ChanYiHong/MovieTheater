package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.QArea;
import ChanuE.MovieTheater.domain.QSpecificArea;
import ChanuE.MovieTheater.domain.SpecificArea;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
}
