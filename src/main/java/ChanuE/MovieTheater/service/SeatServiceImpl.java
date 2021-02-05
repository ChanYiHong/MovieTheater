package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;
import ChanuE.MovieTheater.repository.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;

    @Override
    public List<Seat> makeSeats(int seatNum, Time time) {
        log.info("Make Seats.... : " + seatNum);
        log.info("Time : " + time);

        List<Seat> seats = new ArrayList<>();
        if(seatNum == 100) {
            for(int i = 1; i <= 10; i++) {
                for(int j = 1; j <= 10; j++) {
                    Seat seat = Seat.builder().row(i).col(j).isAvailable(true).time(time).build();
                    seatRepository.save(seat);
                    seats.add(seat);
                }
            }
        }
        else if(seatNum == 120) {
            for(int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 12; j++) {
                    Seat seat = Seat.builder().row(i).col(j).isAvailable(true).time(time).build();
                    seatRepository.save(seat);
                    seats.add(seat);
                }
            }
        }
        return seats;
    }
}
