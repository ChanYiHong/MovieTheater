package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;

import java.util.List;

public interface AreaRepositoryCustom {
    List<Area> findAllAreaByMovieId(Long id);
    List<Area> findAllAreaBySearchCond(AreaSearch areaSearch);
}
