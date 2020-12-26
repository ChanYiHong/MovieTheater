package ChanuE.MovieTheater.dto.date;

import ChanuE.MovieTheater.domain.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class DateResponseDto {

    private Long id;
    private LocalDate localDate;

    public DateResponseDto(Date date){
        this.id = date.getId();
        this.localDate = date.getLocalDate();
    }
}
