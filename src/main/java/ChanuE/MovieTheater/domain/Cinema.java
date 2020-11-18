package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Cinema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cinemaNumber;

    private Display display;

    @Builder
    public Cinema(int cinemaNumber, Display display){
        this.cinemaNumber = cinemaNumber;
        this.display = display;
    }

}
