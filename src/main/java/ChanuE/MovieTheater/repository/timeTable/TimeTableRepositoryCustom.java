package ChanuE.MovieTheater.repository.timeTable;

import ChanuE.MovieTheater.domain.TimeTable;

import java.util.List;

public interface TimeTableRepositoryCustom {
    public List<TimeTable> findAllTimeTableByDateId(Long id);
}
