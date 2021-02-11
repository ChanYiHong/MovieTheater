package ChanuE.MovieTheater.repository.time;

import ChanuE.MovieTheater.domain.Time;
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
        List<Time> result = timeRepository.findApiTime(1L, "서울", "강남");

        for (Time time : result) {
            System.out.println(time);
        }
    }
}