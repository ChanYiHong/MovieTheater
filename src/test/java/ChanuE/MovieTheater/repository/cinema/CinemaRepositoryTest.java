package ChanuE.MovieTheater.repository.cinema;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Display;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.repository.date.DateRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.theater.TheaterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CinemaRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    DateRepository dateRepository;
    @Autowired
    CinemaRepository cinemaRepository;

    @Test
    @Rollback(false)
    public void 상영관등록() throws Exception {
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

    @Test
    public void findCinemaWithSearchCond() throws Exception {
        //List<Cinema> result = cinemaRepository.findCinemaWithMovieTheaterDate("소울", "서울", "강남", LocalDate.of(2021, 2, 5));
        //List<Cinema> result = cinemaRepository.findCinemaWithMovieTheaterDate("소울", "서울", "강남", "2020-02-05");
//        List<Cinema> result = cinemaRepository.findCinemaWithMovieTheater("소울", "서울", "강남");

        List<Cinema> result = cinemaRepository.findCinemaWithMovie("소울");
//
        System.out.println("-------------------------");
        System.out.println(result.size());
        System.out.println("-------------------------");

        for (Cinema cinema : result) {
            System.out.println(cinema);
        }

        List<Cinema> result2 = cinemaRepository.findCinemaWithTheater("서울", "강남");

        System.out.println("-------------------------");
        System.out.println(result2.size());
        System.out.println("-------------------------");

        for (Cinema cinema : result2) {
            System.out.println(cinema);
        }

        List<Cinema> result3 = cinemaRepository.findCinemaWithMovieTheater("소울", "서울", "강남");

        System.out.println("-------------------------");
        System.out.println(result3.size());
        System.out.println("-------------------------");

        for (Cinema cinema : result3) {
            System.out.println(cinema);
        }

//        List<Cinema> result2 = cinemaRepository.findAll();
//        for (Cinema cinema : result2) {
//            System.out.println(cinema);
//            System.out.println(cinema.getMovie());
//            System.out.println(cinema.getTheater());
//        }

        List<Cinema> result4 = cinemaRepository.findCinemaWithMovieTheaterDate("소울", "서울", "강남", LocalDate.of(2021, 2, 5));

        System.out.println("-------------------------");
        System.out.println(result4.size());
        System.out.println("-------------------------");

        for (Cinema cinema : result4) {
            System.out.println(cinema);
        }
    }

}