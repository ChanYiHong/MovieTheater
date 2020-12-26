package ChanuE.MovieTheater.dto.TimeTable;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TimeTableResponseDto {

    private Long id;
    private LocalTime time;

    public TimeTableResponseDto(TimeTable timeTable){
        this.id = timeTable.getId();
        this.time = timeTable.getTime();
    }

}
