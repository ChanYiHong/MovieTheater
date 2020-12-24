package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaSpringDataJpaRepository extends JpaRepository<Area, Long>, AreaRepositoryCustom {

    public List<Area> findAreaByAreaName(String areaName);
}
