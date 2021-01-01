package ChanuE.MovieTheater.dto.reservation;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.domain.ReservationStatus;
import lombok.*;

@Data
@NoArgsConstructor
public class ReservationResponseDto {

    private Long id;
    private String memberName;
    private String movieName;
    private ReservationStatus status;

    @Builder
    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.memberName = reservation.getMember().getMemberName();
        this.movieName = reservation.getMovie().getMovieName();
        this.status = reservation.getStatus();
    }

}
