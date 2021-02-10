package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TheaterServiceTest {

    @Autowired
    TheaterService theaterService;

    @Test
    public void findAllTheaterTest() throws Exception {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResponseDTO<Theater, TheaterDTO> result = theaterService.list(pageRequestDTO);
        for(TheaterDTO dto : result.getDtoList()){
            System.out.println(dto);
        }
    }

    // API 에서 지역 가져오는 테스트. (가공 완료.)
    @Test
    public void findAllTheaterAreaWithMovieIdTest() throws Exception {

        List<TheaterAreaApiDTO> result = theaterService.getAllAreas(3L);
        for (TheaterAreaApiDTO theaterAreaApiDTO : result) {
            System.out.println(theaterAreaApiDTO);
        }
    }
}