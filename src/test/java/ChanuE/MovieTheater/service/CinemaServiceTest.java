package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Display;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaDateApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    public void findDateAPIDataTest() throws Exception {
        List<CinemaDateApiDTO> result = cinemaService.getDateForAPI(1L, "서울", "강남");
        for (CinemaDateApiDTO cinemaDateApiDTO : result) {
            System.out.println(cinemaDateApiDTO);
        }
    }

    @Test
    public void cinemaRemoveTest() throws Exception {
        cinemaService.remove(1L);
    }
}