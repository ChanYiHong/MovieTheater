package ChanuE.MovieTheater.service.seat;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.domain.Time;
import ChanuE.MovieTheater.dto.seat.SeatDTO;

import java.util.List;

public interface SeatService {

    // TimeService에서 Time 생성시, 좌석도 좌석 수 만큼 싹 만들어버린다.
    Time makeSeats(int seatNum, Time time);

    // Time ID로 해당하는 좌석을 전부 가져온다.
    List<SeatDTO> getSeat(Long timeId);

    default SeatDTO entityToDTO(Seat seat) {
        return SeatDTO.builder()
                .id(seat.getId())
                .row(seat.getRow())
                .col(seat.getCol())
                .isAvailable(seat.isAvailable())
                .build();
    }

}
