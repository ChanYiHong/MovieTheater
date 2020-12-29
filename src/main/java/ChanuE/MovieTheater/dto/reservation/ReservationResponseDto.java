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
    private Member member;
    private Movie movie;
    private ReservationStatus status;

    @Builder
    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.member = reservation.getMember();
        this.movie = reservation.getMovie();
        this.status = reservation.getStatus();
    }

}
