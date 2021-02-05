package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Display;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CinemaServiceTest {

    @Autowired
    CinemaService cinemaService;

    @Test
    public void createCinema() throws Exception {
//        CinemaDTO cinemaDTO = CinemaDTO.builder()
//                .name("3관")
//                .movieName("소울")
//                .area("서울")
//                .specificArea("강변")
//                .display(Display.TWO_D)
//                .seats(120)
//                .date(LocalDate.of(2021,2,6))
//                .build();
//
//        cinemaService.save(cinemaSaveDTO, 1L);
    }

}