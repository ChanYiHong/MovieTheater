package ChanuE.MovieTheater.repository.time;

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
        List<LocalTime> result = timeRepository.findApiTime(1L, "서울", "강남");

        for (LocalTime localTime : result) {
            System.out.println(result);
        }
    }
}