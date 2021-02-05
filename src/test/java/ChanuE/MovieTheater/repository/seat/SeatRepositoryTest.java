package ChanuE.MovieTheater.repository.seat;

import ChanuE.MovieTheater.domain.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Test
    public void findSeatWithTimeIdTest() throws Exception {
        Long timeId = 2L;
        List<Seat> result = seatRepository.findByTimeId(timeId);
        for (Seat seat : result) {
            System.out.println(seat);
        }
    }

}