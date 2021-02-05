package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;

import java.util.List;

public interface SeatService {

    // TimeService에서 Time 생성시, 좌석도 좌석 수 만큼 싹 만들어버린다.
    List<Seat> makeSeats(int seatNum, Time time);

}
