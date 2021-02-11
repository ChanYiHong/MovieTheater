package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;
import ChanuE.MovieTheater.repository.seat.SeatRepository;
import ChanuE.MovieTheater.repository.time.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public Time makeSeats(int seatNum, Time time) {
        log.info("Make Seats.... : " + seatNum);
        log.info("Time : " + time);

        // 좌석이 100석일 때.
        if(seatNum == 100) {
            for(int i = 1; i <= 10; i++) {
                for(int j = 1; j <= 10; j++) {
                    Seat seat = Seat.builder().row(i).col(j).isAvailable(true).build();
                    seat.setTime(time);
                    seatRepository.save(seat);
                }
            }
        }
        // 좌석이 120석일 때.
        else if(seatNum == 120) {
            for(int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 12; j++) {
                    Seat seat = Seat.builder().row(i).col(j).isAvailable(true).build();
                    seat.setTime(time);
                    seatRepository.save(seat);
                }
            }
        }
        return time;
    }

    @Override
    public List<SeatDTO> getSeat(Long timeId) {
        List<Seat> result = seatRepository.findByTimeId(timeId);
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
