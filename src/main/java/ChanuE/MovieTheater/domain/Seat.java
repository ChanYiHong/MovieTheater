package ChanuE.MovieTheater.domain;

import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"time", "reservation"})
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(nullable = false)
    private int row;

    @Column(nullable = false)
    private int col;

    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void setTime(Time time) {

        if(this.time != null){
            this.time.getSeats().remove(this);
        }

        this.time = time;
        time.getSeats().add(this);
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        reservation.getSeats().add(this);
    }

}
