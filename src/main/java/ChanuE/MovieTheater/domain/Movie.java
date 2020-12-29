package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY)
    private Reservation reservation;

//    @OneToMany(mappedBy = "movie")
//    private List<TimeTable> timeTables = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Area> areas = new ArrayList<>();

    @Builder
    public Movie(String movieName){
        this.movieName = movieName;
    }


    // == 연관관계 메서드 == //
    // movie(1) <-> reservation(1)  (movie를 더 많이 access할 것 같아서 movie를 foreign key로!)
    public void setReservation(Reservation reservation){
        this.reservation = reservation;
        reservation.setMovie(this);
    }
}
