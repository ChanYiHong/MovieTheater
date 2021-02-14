package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.time.TimeApiDTO;

import java.util.List;

public interface TimeRepositoryCustom {

    List<Time> findTimeByCinemaWithSeats(Long cinemaId);
    List<Time> findApiTime(Long movieId, TimeApiDTO timeApiDTO);

}
