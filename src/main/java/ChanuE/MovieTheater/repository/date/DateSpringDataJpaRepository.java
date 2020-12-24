package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateSpringDataJpaRepository extends JpaRepository<Date, Long> {
}
