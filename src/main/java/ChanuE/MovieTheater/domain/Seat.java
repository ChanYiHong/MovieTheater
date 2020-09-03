package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column
    private boolean isAvailable;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "time_id")
//    private TimeTable timeTable;

    @Builder
    public Seat(String location, boolean isAvailable){
        this.location = location;
        this.isAvailable = isAvailable;
    }


    // == 연관관계 메서드 == //
    // == Seat(다) <--> TimeTable(1)
//
//    public void setTimeTable(TimeTable timeTable){
//        this.timeTable = timeTable;
//        timeTable.getSeats().add(this);
//    }


}
