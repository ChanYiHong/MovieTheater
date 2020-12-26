package ChanuE.MovieTheater.repository.timeTable;

import ChanuE.MovieTheater.domain.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, TimeTableRepositoryCustom {
}
