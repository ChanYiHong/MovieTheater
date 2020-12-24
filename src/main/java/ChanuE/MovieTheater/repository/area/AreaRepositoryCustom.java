package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;

import java.util.List;

public interface AreaRepositoryCustom {
    public List<Area> findAllAreaByMovieId(Long id);
}
