package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class TimeTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    private Date date;

//    @OneToMany(mappedBy = "timeTable")
//    private List<Seat> seats = new ArrayList<>();

    @Builder
    public TimeTable(LocalTime time, Movie movie){
        this.time = time;
        //this.movie = movie;
    }

    // == 연관관계 메서드 == //
    // == TimeTable(다) <--> Date(1)
    public void setDate(Date date){
        this.date = date;
        date.getTimeTables().add(this);
    }
}
