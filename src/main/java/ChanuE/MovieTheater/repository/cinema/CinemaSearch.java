package ChanuE.MovieTheater.repository.cinema;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CinemaSearch {

    private int year;
    private int month;
    private int day;

    public LocalDate getDate() {
        return LocalDate.of(year, month, day);
    }
}
