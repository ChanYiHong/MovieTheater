package ChanuE.MovieTheater.repository.Reservation;

import ChanuE.MovieTheater.domain.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
//
//    @Test
//    void findByMemberName() {
//
//        List<Reservation> result = reservationRepository.findByMemberName("메인관리자");
//
//    }
}