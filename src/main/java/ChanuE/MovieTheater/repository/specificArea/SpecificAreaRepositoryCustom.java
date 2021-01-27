package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;

import java.util.List;

public interface SpecificAreaRepositoryCustom {
    List<SpecificArea> findSpecificAreaByAreaId(Long id);
    List<SpecificArea> findAllBySearchCond(SpecificAreaSearch specificAreaSearch);
}
