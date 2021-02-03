package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}