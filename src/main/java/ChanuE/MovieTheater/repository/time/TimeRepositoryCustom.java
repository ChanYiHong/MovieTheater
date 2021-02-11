package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;

import java.util.List;

public interface TimeRepositoryCustom {

    List<Time> findTimeByCinemaWithSeats(Long cinemaId);
    List<Time> findApiTime(Long movieId, String area, String specificArea);

}
