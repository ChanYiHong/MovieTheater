package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.dto.seat.SeatDTO;
import ChanuE.MovieTheater.dto.time.TimeApiDTO;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;
import ChanuE.MovieTheater.dto.time.TimeSaveDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeServiceTest {

    @Autowired TimeService timeService;

    @Test
    public void setTimeTest() throws Exception {

        Long cinemaId = 1L;
        TimeSaveDTO dto = TimeSaveDTO.builder()
                .cinemaId(cinemaId)
                .seatNum(100)
                .hour(20)
                .minute(30)
                .build();

        Long id = timeService.save(dto);

        System.out.println(id);
    }

    @Test
    public void getTimeTest() throws Exception {

        List<TimeResponseDTO> list = timeService.list(1L);

        for (TimeResponseDTO timeResponseDTO : list) {
            System.out.println(timeResponseDTO.getId());
            System.out.println(timeResponseDTO.getSeatNum());
            System.out.println(timeResponseDTO.getTime());
        }

    }

    @Test
    public void removeTime() throws Exception {
        timeService.remove(6L);
    }

    @Test
    public void timeAPISearchTest() throws Exception {
        TimeApiDTO dto = TimeApiDTO.builder().area("서울").specific("강남").year(2021).month(2).day(27).build();
        List<TimeResponseDTO> result = timeService.listForAPI(1L, dto);
        for (TimeResponseDTO timeResponseDTO : result) {
            System.out.println(timeResponseDTO);
        }
    }
}