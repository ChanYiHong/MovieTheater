package ChanuE.MovieTheater.repository.cinema;

import ChanuE.MovieTheater.domain.Cinema;

import java.time.LocalDate;
import java.util.List;

public interface CinemaRepositoryCustom {

    // 영화 이름, 영화관 지역 이름, 구체적 지명 이름, 날짜 로 영화관 시네마 리스트를 가져옴!
    List<Cinema> findCinemaWithMovieTheaterDate(String movieName, String area, String specificArea, LocalDate localDate);

    // 영화 이름, 영화관 지역 이름, 구체적 지명 이름 으로 영화관 시네마 리스트 가져오기.
    List<Cinema> findCinemaWithMovieTheater(String movieName, String area, String specificArea);

    List<Cinema> findCinemaWithMovie(String movieName);

    List<Cinema> findCinemaWithTheater(String area, String specificArea);
}
