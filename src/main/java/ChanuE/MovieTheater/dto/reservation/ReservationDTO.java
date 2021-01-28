package ChanuE.MovieTheater.dto.reservation;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long id;
    private Long memberId, movieId;
    private String memberName;
    private String movieName;
    private ReservationStatus status;
    private String areaName;
    private String specificAreaName;
    private LocalDateTime movieDate;
    private LocalDateTime createdDate;

}
