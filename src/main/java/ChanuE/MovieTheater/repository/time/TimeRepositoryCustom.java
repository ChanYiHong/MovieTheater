package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.time.TimeApiDTO;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;

import java.time.LocalTime;
import java.util.List;

public interface TimeRepositoryCustom {

    List<Time> findTimeByCinemaWithSeats(Long cinemaId);
    List<LocalTime> findApiTime(Long movieId, String area, String specificArea);

}
