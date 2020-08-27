package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
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
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "timeTable")
    private List<Seat> seats = new ArrayList<>();

    @Builder
    public TimeTable(LocalDateTime time, Movie movie){
        this.time = time;
        this.movie = movie;
    }

    // == 연관관계 메서드 == //
    // == TimeTable(다) <--> Movie(1)

    public void setMovie(Movie movie){
        this.movie = movie;
        movie.getTimeTables().add(this);
    }


}
