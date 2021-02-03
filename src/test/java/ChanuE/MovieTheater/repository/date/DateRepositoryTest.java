package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.repository.cinema.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class DateRepositoryTest {

    @Autowired
    DateRepository dateRepository;
    @Autowired
    CinemaRepository cinemaRepository;

    @Test
    @Rollback(false)
    public void insertDateWithCinema() throws Exception {

        Cinema cinema = cinemaRepository.getOne(3L);
        System.out.println(cinema);

        Date date = Date.builder()
                .localDate(LocalDate.of(2021, 2, 5))
                .cinema(cinema)
                .build();

        dateRepository.save(date);
        Date findDate = dateRepository.getOne(1L);

        System.out.println(findDate);
        System.out.println(findDate.getCinema().getMovie().getMovieName());
    }
}