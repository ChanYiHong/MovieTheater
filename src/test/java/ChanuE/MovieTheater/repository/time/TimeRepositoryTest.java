package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.time.TimeApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeRepositoryTest {

    @Autowired TimeRepository timeRepository;

    @Test
    public void timeApiDataTest() throws Exception {
        TimeApiDTO dto = TimeApiDTO.builder().area("서울").specific("강남").year(2021).month(2).day(27).build();
        List<Time> result = timeRepository.findApiTime(1L, dto);

        for (Time time : result) {
            System.out.println(time);
        }
    }
}