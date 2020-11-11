package ChanuE.MovieTheater.dto.date;

import ChanuE.MovieTheater.domain.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class DateSaveRequestDto {

    private String year;
    private String month;
    private String day;

    @Builder
    public DateSaveRequestDto(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date toEntity(){

        int intYear = Integer.parseInt(this.year);
        int intMonth = Integer.parseInt(this.month);
        int intDay = Integer.parseInt(this.day);
        LocalDate date = LocalDate.of(intYear, intMonth, intDay);

        return Date.builder().localDate(date).build();
    }

}
