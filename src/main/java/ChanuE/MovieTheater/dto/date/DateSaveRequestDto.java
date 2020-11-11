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

    private LocalDate date;

    @Builder
    public DateSaveRequestDto(LocalDate date){
        this.date = date;
    }

    public Date toEntity(){
        return Date.builder().localDate(this.date).build();
    }

}
