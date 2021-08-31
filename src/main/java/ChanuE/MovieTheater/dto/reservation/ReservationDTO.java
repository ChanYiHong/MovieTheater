package ChanuE.MovieTheater.dto.reservation;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long id;
    private String memberId;
    private Long movieId;
    private String memberName;
    private String movieName;
    private LocalDate date;
    private LocalTime time;
    private List<Long> seatId;
    private ReservationStatus status;
    private String area;
    private String specificArea;
    private int totalPerson;
    private int totalPrice;
    private LocalDateTime movieDate;
    private LocalDateTime createdDate;

}
