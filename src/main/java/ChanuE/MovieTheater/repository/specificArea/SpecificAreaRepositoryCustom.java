package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;

import java.util.List;

public interface SpecificAreaRepositoryCustom {
    public List<SpecificArea> findSpecificAreaByAreaId(Long id);
}
