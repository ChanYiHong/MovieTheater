package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MovieSearch {

    private String title;
    private AgeLimit ageLimit;

}
