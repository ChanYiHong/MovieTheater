package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificAreaRepository extends JpaRepository<SpecificArea, Long>, SpecificAreaRepositoryCustom {

}
