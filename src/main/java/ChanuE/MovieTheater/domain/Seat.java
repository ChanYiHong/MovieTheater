package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"time"})
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

    public void setTime(Time time) {

        if(this.time != null){
            this.time.getSeats().remove(this);
        }

        this.time = time;
        time.getSeats().add(this);
    }
}
