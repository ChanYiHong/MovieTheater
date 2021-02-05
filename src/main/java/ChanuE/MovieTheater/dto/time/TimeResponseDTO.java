package ChanuE.MovieTheater.dto.time;

import ChanuE.MovieTheater.domain.Seat;
import ChanuE.MovieTheater.dto.seat.SeatDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeResponseDTO {

    private Long id;
    private LocalTime time;
    private Long cinemaId;
    private int seatNum;

}
