package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.repository.area.AreaRepository;
import ChanuE.MovieTheater.repository.cinema.CinemaRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TheaterRepositoryTest {
    
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CinemaRepository cinemaRepository;

    // 영화관에 지역이 포함
    @Test
    public void 상영관생성() throws Exception {
        Movie movie = movieRepository.getOne(1L);
        Theater theater = theaterRepository.getOne(1L);
        Cinema cinema = Cinema.builder()
                .display(Display.TWO_D)
                .movie(movie)
                .theater(theater)
                .name("1관")
                .seats(120)
                .build();

        cinemaRepository.save(cinema);

        Cinema findCinema = cinemaRepository.getOne(1L);
        System.out.println(findCinema);
        System.out.println(findCinema.getMovie().getMovieName());
        System.out.println(findCinema.getTheater().getName());
    }

}