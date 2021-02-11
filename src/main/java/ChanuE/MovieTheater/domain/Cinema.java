package ChanuE.MovieTheater.domain;

import ChanuE.MovieTheater.converter.DisplayConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"movie", "theater"})
public class Cinema extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long id;

    private String name;

    @Convert(converter = DisplayConverter.class)
    private Display display;

    private LocalDate date;

    private int seats;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theater theater;

    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public void changeMovie(Movie movie){
        this.movie = movie;
    }

    public void changeTheater(Theater theater){
        this.theater = theater;
    }
}
